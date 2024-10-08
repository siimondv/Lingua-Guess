package com.example.linguaguess.data.local.datasource

import com.example.linguaguess.data.local.dao.ScoreDao
import com.example.linguaguess.data.local.model.ScoreEntity
import com.example.linguaguess.utils.Constants
import com.example.linguaguess.utils.NetworkResult
import com.example.linguaguess.utils.NetworkResultLoading
import javax.inject.Inject


class LocalScoreDataSource @Inject constructor(
    private val scoreDao: ScoreDao
) {


    suspend fun getScoreByCollectionChapterAndBlock(
        collectionId: Long,
        chapterId: Long,
        blockPosition: Int
    ): NetworkResult<ScoreEntity?> {
        return try {
            val score =
                scoreDao.getScoreByCollectionChapterAndBlock(collectionId, chapterId, blockPosition)
            NetworkResult.Success(score)
        } catch (e: Exception) {
            NetworkResult.Error(Constants.SCORE_NOT_RETRIEVED)
        }
    }

    suspend fun getScoreByCollectionAndChapter(
        collectionId: Long,
        chapterId: Long,
    ): NetworkResult<List<ScoreEntity>> {
        return try {
            val score = scoreDao.getScoreByCollectionAndChapter(collectionId, chapterId)
            NetworkResult.Success(score)
        } catch (e: Exception) {
            NetworkResult.Error(Constants.SCORE_NOT_RETRIEVED)
        }
    }

    suspend fun insertScore(scoreEntity: ScoreEntity): NetworkResult<Unit> {
        return try {
            scoreDao.insertScore(scoreEntity)
            NetworkResult.Success(Unit)
        } catch (e: Exception) {
            NetworkResult.Error(Constants.SCORE_NOT_SAVED)
        }
    }

    suspend fun updateScore(
        collectionId: Long,
        chapterId: Long,
        blockPosition: Int,
        rightAnswers: Int
    ): NetworkResultLoading<Unit> {
        return try {
            scoreDao.updateRightAnswers(collectionId, chapterId, blockPosition, rightAnswers)
            NetworkResultLoading.Success(Unit)
        } catch (e: Exception) {
            NetworkResultLoading.Error(Constants.SCORE_NOT_UPDATED)
        }
    }

}