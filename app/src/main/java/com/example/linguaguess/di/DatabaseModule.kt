package com.example.linguaguess.di

import android.content.Context
import androidx.room.Room
import com.example.linguaguess.data.local.dao.ChapterDao
import com.example.linguaguess.data.local.dao.CollectionDao
import com.example.linguaguess.data.local.dao.CollectionWithChaptersAndWordsDao
import com.example.linguaguess.data.local.dao.JapaneseWordDao
import com.example.linguaguess.data.local.dao.ScoreDao
import com.example.linguaguess.data.local.database.CollectionDatabase
import com.example.linguaguess.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideCollectionDatabase(@ApplicationContext context: Context): CollectionDatabase {
        return Room.databaseBuilder(
            context,
            CollectionDatabase::class.java,
            Constants.LOCAL_DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideChapterDao(appDatabase: CollectionDatabase): ChapterDao {
        return appDatabase.chapterDao
    }

    @Provides
    fun provideJapaneseWordDao(appDatabase: CollectionDatabase): JapaneseWordDao {
        return appDatabase.japaneseWordDao
    }

    @Provides
    fun provideCollectionDao(appDatabase: CollectionDatabase): CollectionDao {
        return appDatabase.collectionDao
    }

    @Provides
    fun provideCollectionWithChaptersAndWordsDao(appDatabase: CollectionDatabase): CollectionWithChaptersAndWordsDao {
        return appDatabase.collectionWithChaptersAndWordsDao
    }

    @Provides
    fun provideScoreDao(appDatabase: CollectionDatabase): ScoreDao {
        return appDatabase.scoreDao
    }
}