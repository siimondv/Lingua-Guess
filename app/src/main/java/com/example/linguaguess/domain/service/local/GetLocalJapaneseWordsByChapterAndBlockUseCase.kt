package com.example.linguaguess.domain.service.local

import com.example.linguaguess.data.local.repository.LocalJapaneseWordRepo
import com.example.linguaguess.data.mappers.toJapaneseWord
import com.example.linguaguess.domain.model.JapaneseWord
import com.example.linguaguess.utils.Constants
import com.example.linguaguess.utils.NetworkResultLoading
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetLocalJapaneseWordsByChapterAndBlockUseCase @Inject constructor(
    private val localJapaneseWordRepo: LocalJapaneseWordRepo,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend operator fun invoke(
        chapterId: Long,
        blockPosition: Long
    ): Flow<NetworkResultLoading<List<JapaneseWord>>> {
        return flow {
            emit(NetworkResultLoading.Loading())

            val result = localJapaneseWordRepo.getJapaneseWordsByChapterIdBlockPosition(
                chapterId,
                blockPosition
            )
            if (result is NetworkResultLoading.Success) {
                val collections =
                    result.data?.map { it.toJapaneseWord() } ?: emptyList()
                emit(NetworkResultLoading.Success(collections))
            } else {
                emit(NetworkResultLoading.Error(result.message ?: Constants.ERROR))
            }
        }.flowOn(dispatcher)
    }
}
