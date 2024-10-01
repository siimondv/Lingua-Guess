package com.example.linguaguess.domain.service.local.internal

import com.example.linguaguess.data.local.datasource.LocalChapterDataSource
import com.example.linguaguess.data.local.datasource.LocalScoreDataSource
import com.example.linguaguess.data.local.model.ScoreEntity
import com.example.linguaguess.domain.model.Block
import com.example.linguaguess.utils.Constants
import com.example.linguaguess.utils.NetworkResult
import com.example.linguaguess.utils.NetworkResultLoading
import javax.inject.Inject


class UpdateLocalBLocksByCollectionChapterUseCase @Inject constructor(
    private val localChapterDataSource: LocalChapterDataSource,
    private val localScoreDataSource: LocalScoreDataSource,
) {

    //TODO mirar si este usecase se debe dividir en usecases especficos y desde uno mayor llarmar a los otros
    suspend operator fun invoke(
        collectionId: Long,
        chapterId: Long,
    ): NetworkResult<Unit> {
        val result = localChapterDataSource.getTotalWordsByChapterId(chapterId)

        if (result is NetworkResultLoading.Success) {
            val blocks = result.data?.let { createBlocks(it) } ?: emptyList()

            blocks.map { block ->
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


            return NetworkResult.Success(Unit)


        } else {
            return NetworkResult.Error(Constants.ERROR)
        }

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

    //TODO Estos metodos estan exactamente iguales que en el usecase de GetUpdateLocalBlocksByCollectionChapterUseCase.kt, asi quew lo mejor probablemente sea crear usecases de estos para no reutilizarlso
    private suspend fun checkIfScoreExists(
        collectionId: Long,
        chapterId: Long,
        blockPosition: Int
    ): Boolean {
        val result = localScoreDataSource.getScoreByCollectionChapterAndBlock(
            collectionId,
            chapterId,
            blockPosition
        )

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
        val result = localScoreDataSource.getScoreByCollectionChapterAndBlock(
            collectionId,
            chapterId,
            blockPosition
        )

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
