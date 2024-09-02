package com.example.linguaguess.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.linguaguess.data.local.model.ChapterEntity
import com.example.linguaguess.data.local.model.JapaneseWordEntity

@Dao
interface JapaneseWordDao {

    @Query("SELECT * FROM JapaneseWordEntity WHERE chapterId = :chapterId")
    suspend fun getWordsByChapterNumber(chapterId: Int): List<JapaneseWordEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(japaneseWordEntity: JapaneseWordEntity)
}