package com.example.linguaguess.data.remote.tokenrelated

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


class AuthInterceptor @Inject constructor(
    private val tokenManager: TokenManager,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking {
            tokenManager.getAccessToken().first()
        }

        val request = chain.request().newBuilder().header("Authorization", "Bearer $token")
        return chain.proceed(request.build())
    }
}