package com.example.linguaguess.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.linguaguess.data.local.model.ScoreEntity

@Dao
interface ScoreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertScore(score: ScoreEntity)


    @Query("UPDATE ScoreEntity SET rightAnswers = :rightAnswers WHERE collectionId = :collectionId AND chapterId = :chapterId AND blockPosition = :blockPosition")
    suspend fun updateRightAnswers(
        collectionId: Long,
        chapterId: Long,
        blockPosition: Int,
        rightAnswers: Int
    )


    @Query("UPDATE ScoreEntity SET totalAnswers = :totalAnswers WHERE collectionId = :collectionId AND chapterId = :chapterId AND blockPosition = :blockPosition")
    suspend fun updateTotalAnswers(
        collectionId: Long,
        chapterId: Long,
        blockPosition: Int,
        totalAnswers: Int
    )

    @Query("SELECT * FROM ScoreEntity WHERE collectionId = :collectionId AND chapterId = :chapterId AND blockPosition = :blockPosition")
    suspend fun getScoreByCollectionChapterAndBlock(
        collectionId: Long,
        chapterId: Long,
        blockPosition: Int
    ): ScoreEntity?

    @Query("SELECT * FROM ScoreEntity WHERE collectionId = :collectionId AND chapterId = :chapterId")
    suspend fun getScoreByCollectionAndChapter(
        collectionId: Long,
        chapterId: Long
    ): List<ScoreEntity>

}