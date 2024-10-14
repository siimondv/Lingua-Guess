package com.example.linguaguess.data.local.repository.implementations

import com.example.linguaguess.data.local.dao.ScoreDao
import com.example.linguaguess.data.local.model.ScoreEntity
import com.example.linguaguess.data.local.repository.interfaces.LocalScoreRepo
import com.example.linguaguess.utils.Constants
import com.example.linguaguess.utils.NetworkResult
import com.example.linguaguess.utils.NetworkResultLoading
import javax.inject.Inject


class LocalScoreRepoImp(
    private val scoreDao: ScoreDao
) : LocalScoreRepo {


    override suspend fun getScoreByCollectionChapterAndBlock(
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


    override suspend fun insertScore(scoreEntity: ScoreEntity): NetworkResult<Unit> {
        return try {
            scoreDao.insertScore(scoreEntity)
            NetworkResult.Success(Unit)
        } catch (e: Exception) {
            NetworkResult.Error(Constants.SCORE_NOT_SAVED)
        }
    }

    override suspend fun updateScore(
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

