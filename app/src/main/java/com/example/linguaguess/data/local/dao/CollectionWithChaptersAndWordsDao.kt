package com.example.linguaguess.data.local.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Transaction
import com.example.linguaguess.data.local.model.ChapterEntity
import com.example.linguaguess.data.local.model.CollectionEntity
import com.example.linguaguess.data.local.model.JapaneseWordEntity

interface CollectionWithChaptersAndWordsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCollection(collection: CollectionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChapter(chapter: ChapterEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJapaneseWord(japaneseWord: JapaneseWordEntity)

    @Transaction
    suspend fun insertCollectionWithChaptersAndWords(
        collection: CollectionEntity,
        chapters: List<ChapterEntity>,
        words: List<JapaneseWordEntity>
    ) {
        insertCollection(collection)
        chapters.forEach { chapter ->
            insertChapter(chapter)
            words.filter { it.chapterId == chapter.chapterId }.forEach { word ->
                insertJapaneseWord(word)
            }
        }

    }
}