package com.example.linguaguess.domain.service.local

import com.example.linguaguess.domain.model.Block
import com.example.linguaguess.domain.service.local.internal.GetAndUpdateLocalBlocksByCollectionChapter
import com.example.linguaguess.utils.Constants
import com.example.linguaguess.utils.NetworkResult
import com.example.linguaguess.utils.NetworkResultLoading
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetAndUpdateLocalBlocksByCollectionChapterUseCase @Inject constructor(
    private val getAndUpdateLocalBlocksByCollectionChapter: GetAndUpdateLocalBlocksByCollectionChapter,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {


    suspend operator fun invoke(
        collectionId: Long,
        chapterId: Long,
    ): Flow<NetworkResultLoading<List<Block>>> {
        return flow {
            emit(NetworkResultLoading.Loading())

            val result = getAndUpdateLocalBlocksByCollectionChapter(collectionId, chapterId)
            if (result is NetworkResult.Success) {
                val blocks: List<Block> = result.data ?: emptyList()
                emit(NetworkResultLoading.Success(blocks))
            } else {
                emit(NetworkResultLoading.Error(Constants.ERROR))
            }

        }.flowOn(dispatcher)
    }





}