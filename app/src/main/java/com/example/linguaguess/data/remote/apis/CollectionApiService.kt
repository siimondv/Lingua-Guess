package com.example.linguaguess.data.remote.apis

import com.example.linguaguess.data.remote.model.CollectionDetailDto
import com.example.linguaguess.data.remote.model.CollectionDto
import com.example.linguaguess.domain.model.Page
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CollectionApiService {

    // Endpoint for fetching all collectionJS with pagination
    @GET("collections")
    suspend fun getAllCollections(
        @Query("page") page: Int = 0, @Query("size") size: Int = 10
    ): Response<Page<CollectionDto>>

    // Endpoint for fetching collectionJ details by ID
    @GET("collections/{collectionId}")
    suspend fun getCollectionById(
        @Path("collectionId") collectionId: Long
    ): Response<CollectionDetailDto>
}