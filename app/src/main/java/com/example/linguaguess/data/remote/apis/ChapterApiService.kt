package com.example.linguaguess.data.remote.apis


import com.example.linguaguess.data.remote.model.ChapterDto
import com.example.linguaguess.domain.model.Page
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ChapterApiService {
    @GET("chapters/{collectionId}")
    suspend fun getChaptersByCollectionId(
        @Path("collectionId") collectionId: Long,
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 10
    ): Response<Page<ChapterDto>>

}