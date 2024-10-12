package com.example.linguaguess.domain.service.remote

import com.example.linguaguess.data.local.dao.CollectionDao
import com.example.linguaguess.data.remote.repository.RemoteCollectionRepo
import com.example.linguaguess.data.remote.model.CollectionDetailDto
import com.example.linguaguess.utils.NetworkResultLoading
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetRemoteCollectionUseCase @Inject constructor(
    private val remoteCollectionRepo: RemoteCollectionRepo,
    private val collectionDao: CollectionDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    operator fun invoke(id: Long): Flow<NetworkResultLoading<CollectionDetailDto>> {
        return flow {
            emit(NetworkResultLoading.Loading())
            // Check if the collection exists in the local database
            val exists = collectionDao.exists(id)
            if (exists) {
                var i = 1
            }
            // Get the collection details from the data source
            val result = remoteCollectionRepo.getCollectionById(id)

            // Process the result if it is successful
            if (result is NetworkResultLoading.Success) {
                // If the collection exists, update the `isDownloaded` property
                if (exists) {
                    result.data?.isDownloaded = true

                }
            }
            emit(result)
            // Emit the final result

        }.flowOn(dispatcher)

    }
}