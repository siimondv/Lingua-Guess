package com.example.linguaguess.data.remote.datasource

import com.example.linguaguess.data.remote.apis.CollectionApiService
import com.example.linguaguess.data.remote.model.CollectionDetailDto
import com.example.linguaguess.data.remote.model.CollectionDto
import com.example.linguaguess.domain.model.Page
import com.example.linguaguess.utils.Constants
import com.example.linguaguess.utils.NetworkResult
import com.example.linguaguess.utils.NetworkResultLoading
import javax.inject.Inject


class RemoteCollectionDataSource @Inject constructor(
    private val collectionApiService: CollectionApiService
) {

    suspend fun getAllCollections(
        page: Int = 0
    ): NetworkResult<Page<CollectionDto>> {
        val response = collectionApiService.getAllCollections(page = page)

        return if (response.isSuccessful) {
            response.body()?.let {
                NetworkResult.Success(it)
            } ?: NetworkResult.Error(Constants.COLLECTIONS_NOT_FOUND)
        } else {
            NetworkResult.Error(response.message())
        }
    }

    suspend fun getCollectionById(id: Long): NetworkResultLoading<CollectionDetailDto> {
        val response = collectionApiService.getCollectionById(id)

        return if (response.isSuccessful) {
            response.body()?.let {
                NetworkResultLoading.Success(it)
            } ?: NetworkResultLoading.Error(Constants.COLLECTION_NOT_FOUND)
        } else {
            NetworkResultLoading.Error(response.message())
        }
    }

}