package com.example.linguaguess.data.remote.repository

import com.example.linguaguess.data.remote.apis.AuthApiService
import com.example.linguaguess.data.remote.model.LoginResponseDTO
import com.example.linguaguess.data.remote.tokenrelated.TokenManager
import com.example.linguaguess.domain.model.Credentials
import com.example.linguaguess.domain.model.User
import com.example.linguaguess.utils.Constants
import com.example.linguaguess.utils.NetworkResultLoading
import javax.inject.Inject

class RemoteAuthRepo @Inject constructor(
    private val authApiService: AuthApiService,
    private val tokenManager: TokenManager
) {

    suspend fun login(
        credentials: Credentials
    ): NetworkResultLoading<Unit> {


        return try {
            val response = authApiService.login(credentials)

            if (response.isSuccessful) {
                response.body()?.let {
                    response.body()?.let { fetchTokens(it) }
                    NetworkResultLoading.Success(Unit)
                } ?: NetworkResultLoading.Error(Constants.NO_USER_FOUND)
            } else {
                NetworkResultLoading.Error(response.message())
            }
        } catch (e: Exception) {
            NetworkResultLoading.Error(Constants.NO_USER_FOUND)
        }
    }

    suspend fun register(
        user: User
    ): NetworkResultLoading<Unit> {
        return try {
            val response = authApiService.register(user)

            if (response.isSuccessful) {
                NetworkResultLoading.Success(Unit)
            } else {
                NetworkResultLoading.Error(response.message())
            }
        } catch (e: Exception) {
            NetworkResultLoading.Error(Constants.NO_USER_FOUND)
        }
    }

    private suspend fun fetchTokens(loginResponseDTO: LoginResponseDTO) {
        val accesstoken = loginResponseDTO.accessToken
        val refreshToken = loginResponseDTO.refreshToken
        tokenManager.saveAccessToken(accesstoken)
        tokenManager.saveRefreshToken(refreshToken)
    }


}