package com.example.linguaguess.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.linguaguess.data.local.dao.ChapterDao
import com.example.linguaguess.data.local.dao.CollectionDao
import com.example.linguaguess.data.local.dao.JapaneseWordDao
import com.example.linguaguess.data.local.model.ChapterEntity
import com.example.linguaguess.data.local.model.CollectionEntity
import com.example.linguaguess.data.local.model.JapaneseWordEntity


@Database(
    entities = [CollectionEntity::class, ChapterEntity::class, JapaneseWordEntity::class],
    version = 1
)
abstract class CollectionDatabase : RoomDatabase() {
    // DAO for CollectionEntity
    abstract val collectionDao: CollectionDao

    // DAO for ChapterEntity
    abstract val chapterDao: ChapterDao

    // DAO for JapaneseWordEntity
    abstract val japaneseWordDao: JapaneseWordDao
}