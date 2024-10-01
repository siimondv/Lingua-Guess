package com.example.linguaguess.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Transaction
import com.example.linguaguess.data.local.model.ChapterEntity
import com.example.linguaguess.data.local.model.CollectionEntity
import com.example.linguaguess.data.local.model.JapaneseWordEntity

@Dao
interface CollectionWithChaptersAndWordsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCollection(collection: CollectionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListChapters(chapters: List<ChapterEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJapaneseWord(japaneseWord: JapaneseWordEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListJapaneseWords(japaneseWordEntity: List<JapaneseWordEntity>)

    @Transaction
    suspend fun insertCollectionWithChaptersAndWords(
        collection: CollectionEntity,
        chapters: List<ChapterEntity>,
        words: List<JapaneseWordEntity>
    ) {
        try {
            insertCollection(collection)
            insertListChapters(chapters)
            insertListJapaneseWords(words)
        } catch (e: Exception) {
            throw e
        }

    }
}