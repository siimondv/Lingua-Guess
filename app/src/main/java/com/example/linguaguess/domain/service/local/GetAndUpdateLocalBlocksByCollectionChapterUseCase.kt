package com.example.linguaguess.domain.service.local

import com.example.linguaguess.data.local.datasource.LocalChapterDataSource
import com.example.linguaguess.data.local.datasource.LocalScoreDataSource
import com.example.linguaguess.data.local.model.ScoreEntity
import com.example.linguaguess.domain.model.Block
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
    private val localChapterDataSource: LocalChapterDataSource,
    private val localScoreDataSource: LocalScoreDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    //TODO mirar si este usecase se debe dividir en usecases especficos y desde uno mayor llarmar a los otros
    suspend operator fun invoke(
        collectionId: Long,
        chapterId: Long,
    ): Flow<NetworkResultLoading<List<Block>>> {
        return flow {
            emit(NetworkResultLoading.Loading())

            val result = localChapterDataSource.getTotalWordsByChapterId(chapterId)

            if (result is NetworkResultLoading.Success) {
                val blocks = result.data?.let { createBlocks(it) } ?: emptyList()

                val updatedBlocks = blocks.map { block ->
                    val scoreExists =
                        checkIfScoreExists(collectionId, chapterId, block.blockPosition)

                    //Here it enters if the score exists and checks wether the right answers are initialized
                    if (scoreExists) {
                        val scoreEntity = getScoreEntityIfRightAnswersIsInitialized(
                            collectionId,
                            chapterId,
                            block.blockPosition
                        )
                        if (scoreEntity != null) {
                            block.copy(
                                isStarted = true,
                                correctWords = scoreEntity.rightAnswers ?: 0
                            )
                        } else {
                            block
                        }
                    }
                    //Here it enters if the score does not exist (first time entering the chapter or if the chapter has added new words)
                    else {
                        localScoreDataSource.insertScore(
                            ScoreEntity(
                                collectionId = collectionId,
                                chapterId = chapterId,
                                blockPosition = block.blockPosition,
                                rightAnswers = null,
                                totalAnswers = block.totalWords
                            )
                        )
                        block
                    }
                }


                emit(NetworkResultLoading.Success(updatedBlocks))


            } else {
                emit(NetworkResultLoading.Error(Constants.ERROR))
            }

        }.flowOn(dispatcher)
    }

    private fun createBlocks(totalWord: Int): List<Block> {
        val blocks = mutableListOf<Block>()

        // Calculate how many blocks of 30 words you need
        val numberOfBlocks = (totalWord + 29) / 30  // Ceiling division

        for (i in 0 until numberOfBlocks) {
            // Calculate the total words for each block, handling the last block case
            val wordsInBlock = if ((i + 1) * 30 <= totalWord) 30 else totalWord % 30

            // Create and add the block to the list
            blocks.add(Block(blockPosition = i + 1, totalWords = wordsInBlock))
        }

        return blocks
    }

    private suspend fun checkIfScoreExists(
        collectionId: Long,
        chapterId: Long,
        blockPosition: Int
    ): Boolean {
        val result = localScoreDataSource.getScoreByCollectionChapterAndBlock(collectionId, chapterId, blockPosition)

        if (result is NetworkResult.Success) {
            return result.data != null
        }
        return false

    }

    private suspend fun getScoreEntityIfRightAnswersIsInitialized(
        collectionId: Long,
        chapterId: Long,
        blockPosition: Int
    ): ScoreEntity? {
        val result = localScoreDataSource.getScoreByCollectionChapterAndBlock(collectionId, chapterId, blockPosition)

        if (result is NetworkResult.Success) {
            val data = result.data
            if (data?.rightAnswers != null) {
                return result.data
            }
            return null
        }
        return null

    }


}