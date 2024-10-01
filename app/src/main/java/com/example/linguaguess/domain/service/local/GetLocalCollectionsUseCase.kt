package com.example.linguaguess.domain.service.local

import com.example.linguaguess.data.local.datasource.LocalCollectionDataSource
import com.example.linguaguess.data.local.model.CollectionEntity
import com.example.linguaguess.data.mappers.toCollectionJ
import com.example.linguaguess.domain.model.CollectionJ
import com.example.linguaguess.utils.Constants
import com.example.linguaguess.utils.NetworkResultLoading
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetLocalCollectionsUseCase @Inject constructor(
    private val localCollectionDataSource: LocalCollectionDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend operator fun invoke(): Flow<NetworkResultLoading<List<CollectionJ>>> {
        return flow {
            emit(NetworkResultLoading.Loading())

            val result = localCollectionDataSource.getAllCollections()
            if (result is NetworkResultLoading.Success) {
                val collections =
                    result.data?.map { it.toCollectionJ().copy(isDownloaded = true) } ?: emptyList()
                emit(NetworkResultLoading.Success(collections))
            } else {
                emit(NetworkResultLoading.Error(result.message ?: Constants.ERROR))
            }


        }.flowOn(dispatcher)

    }
}