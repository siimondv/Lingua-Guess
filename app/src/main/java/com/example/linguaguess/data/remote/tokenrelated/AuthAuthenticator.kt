package com.example.linguaguess.data.remote.tokenrelated

import com.example.linguaguess.data.remote.apis.AuthApiService
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class AuthAuthenticator @Inject constructor(
    private val authApiService: AuthApiService,
    private val tokenManager: TokenManager
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val token = runBlocking {
            tokenManager.getRefreshToken().first()
        }
        return runBlocking {
            val newToken = authApiService.refresh("$token")

            if (newToken.isSuccessful) {
                val accesstoken = newToken.body()?.accessToken
                if (accesstoken != null) {
                    tokenManager.saveAccessToken(accesstoken)
                }
                response.request.newBuilder()
                    .header("Authorization", "Bearer $accesstoken")
                    .build()
            } else {
                null
            }
        }

    }


}