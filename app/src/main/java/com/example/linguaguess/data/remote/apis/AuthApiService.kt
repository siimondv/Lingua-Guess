package com.example.linguaguess.data.remote.apis

import com.example.linguaguess.data.remote.model.LoginResponseDTO
import com.example.linguaguess.domain.model.Credentials
import com.example.linguaguess.domain.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApiService {
    @POST("login")
    suspend fun login(
        @Body credentials: Credentials
    ): Response<LoginResponseDTO>

    @POST("register")
    suspend fun register(
        @Body user: User
    ): Response<Unit>

    @GET("refresh")
    suspend fun refresh(@Query("refreshToken") token: String): Response<LoginResponseDTO>
}