package com.example.linguaguess.domain.service.remote

import com.example.linguaguess.data.local.dao.CollectionDao
import com.example.linguaguess.data.remote.model.CollectionDto
import com.example.linguaguess.data.remote.repository.interfaces.RemoteCollectionRepo
import com.example.linguaguess.domain.model.Page
import com.example.linguaguess.utils.NetworkResult
import javax.inject.Inject

class GetRemoteCollectionsUseCase @Inject constructor(
    private val remoteCollectionRepo: RemoteCollectionRepo,
    private val collectionDao: CollectionDao,
) {

    suspend operator fun invoke(page: Int): NetworkResult<Page<CollectionDto>> {

        val result = remoteCollectionRepo.getAllCollections(page)


        if (result is NetworkResult.Success) {
            val collections = result.data?.content?.map { collectionDto ->
                val exists = collectionDto.collectionId.let { collectionDao.exists(it) }
                if (exists) {
                    collectionDto.isDownloaded = true
                }
                collectionDto
            }
            // Update the result with the modified collections
            result.data?.content = collections ?: emptyList()
        }

        return result
    }
}