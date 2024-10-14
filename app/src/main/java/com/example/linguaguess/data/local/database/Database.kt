package com.example.linguaguess.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.linguaguess.data.local.dao.ChapterDao
import com.example.linguaguess.data.local.dao.CollectionDao
import com.example.linguaguess.data.local.dao.CollectionWithChaptersAndWordsDao
import com.example.linguaguess.data.local.dao.JapaneseWordDao
import com.example.linguaguess.data.local.dao.ScoreDao
import com.example.linguaguess.data.local.model.CollectionEntity
import com.example.linguaguess.data.local.model.ChapterEntity
import com.example.linguaguess.data.local.model.JapaneseWordEntity
import com.example.linguaguess.data.local.model.ScoreEntity

@Database(
    entities = [CollectionEntity::class, ChapterEntity::class, JapaneseWordEntity::class, ScoreEntity::class],
    version = 1
)
abstract class CollectionDatabase : RoomDatabase() {

    abstract val collectionDao: CollectionDao


    abstract val chapterDao: ChapterDao


    abstract val japaneseWordDao: JapaneseWordDao


    abstract val collectionWithChaptersAndWordsDao: CollectionWithChaptersAndWordsDao


    abstract val scoreDao: ScoreDao
}