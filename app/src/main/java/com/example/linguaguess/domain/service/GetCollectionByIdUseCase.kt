package com.example.linguaguess.domain.service

import com.example.linguaguess.data.remote.datasource.CollectionDataSource
import com.example.linguaguess.data.remote.model.CollectionDetailDto
import com.example.linguaguess.data.remote.model.CollectionDto
import com.example.linguaguess.domain.model.CollectionJ
import com.example.linguaguess.utils.NetworkResult
import com.example.linguaguess.utils.NetworkResultLoading
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetCollectionByIdUseCase @Inject constructor(
    private val collectionDataSource: CollectionDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    operator fun invoke(id: Long): Flow<NetworkResultLoading<CollectionDetailDto>> {
        return flow {
            emit(NetworkResultLoading.Loading())
            emit(collectionDataSource.getCollectionById(id))
        }.flowOn(dispatcher)

    }
}