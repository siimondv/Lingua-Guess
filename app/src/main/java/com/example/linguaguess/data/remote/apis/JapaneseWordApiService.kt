package com.example.linguaguess.data.remote.apis

import com.example.linguaguess.data.remote.model.JapaneseWordDto
import com.example.linguaguess.domain.model.Page
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JapaneseWordApiService {
    @GET("japaneseWords/{chapterId}")
    suspend fun getAllJapaneseWordsByChapterId(
        @Path("chapterId") chapterId: Long,
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 100
    ): Response<Page<JapaneseWordDto>>
}