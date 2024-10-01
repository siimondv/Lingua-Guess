package com.example.linguaguess.domain.service.local

import com.example.linguaguess.data.local.datasource.LocalScoreDataSource
import com.example.linguaguess.utils.NetworkResult
import com.example.linguaguess.utils.NetworkResultLoading
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UpdateLocalScoreUseCase @Inject constructor(
    private val localScoreDataSource: LocalScoreDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend operator fun invoke(
        collectionId: Long,
        chapterId: Long,
        blockPosition: Int,
        rightAnswers: Int,
    ): Flow<NetworkResultLoading<Unit>> {


        return flow {
            emit(NetworkResultLoading.Loading())

            val result = localScoreDataSource.getScoreByCollectionChapterAndBlock(
                collectionId,
                chapterId,
                blockPosition
            )

            if (result is NetworkResult.Success) {
                result.data?.let {
                    if (it.rightAnswers != null && it.rightAnswers < rightAnswers) {
                        emit(
                            localScoreDataSource.updateScore(
                                collectionId,
                                chapterId,
                                blockPosition,
                                rightAnswers
                            )
                        )
                    } else if (it.rightAnswers == null) {

                        emit(
                            localScoreDataSource.updateScore(
                                collectionId,
                                chapterId,
                                blockPosition,
                                rightAnswers
                            )
                        )
                    } else {
                        emit(NetworkResultLoading.Success(Unit))
                    }

                }
            } else {
                emit(NetworkResultLoading.Error(data = Unit))
            }


        }.flowOn(dispatcher)

    }
}