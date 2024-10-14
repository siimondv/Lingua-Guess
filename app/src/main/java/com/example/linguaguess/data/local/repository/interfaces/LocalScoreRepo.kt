package com.example.linguaguess.data.local.repository.interfaces

import com.example.linguaguess.data.local.model.ScoreEntity
import com.example.linguaguess.utils.NetworkResult
import com.example.linguaguess.utils.NetworkResultLoading

interface LocalScoreRepo {
    suspend fun getScoreByCollectionChapterAndBlock(
        collectionId: Long,
        chapterId: Long,
        blockPosition: Int
    ): NetworkResult<ScoreEntity?>

    suspend fun insertScore(scoreEntity: ScoreEntity): NetworkResult<Unit>

    suspend fun updateScore(
        collectionId: Long,
        chapterId: Long,
        blockPosition: Int,
        rightAnswers: Int
    ): NetworkResultLoading<Unit>
}