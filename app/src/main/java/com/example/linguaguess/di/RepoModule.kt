package com.example.linguaguess.di

import android.content.Context
import com.example.linguaguess.BuildConfig
import com.example.linguaguess.data.local.dao.ChapterDao
import com.example.linguaguess.data.local.dao.CollectionDao
import com.example.linguaguess.data.local.dao.CollectionWithChaptersAndWordsDao
import com.example.linguaguess.data.local.dao.JapaneseWordDao
import com.example.linguaguess.data.local.dao.ScoreDao
import com.example.linguaguess.data.local.repository.implementations.LocalChapterRepoImp
import com.example.linguaguess.data.local.repository.implementations.LocalCollectionRepoImp
import com.example.linguaguess.data.local.repository.implementations.LocalCollectionWithChaptersAndWordsRepoImp
import com.example.linguaguess.data.local.repository.implementations.LocalJapaneseWordRepoImp
import com.example.linguaguess.data.local.repository.implementations.LocalScoreRepoImp
import com.example.linguaguess.data.local.repository.interfaces.LocalChapterRepo
import com.example.linguaguess.data.local.repository.interfaces.LocalCollectionRepo
import com.example.linguaguess.data.local.repository.interfaces.LocalCollectionWithChaptersAndWordsRepo
import com.example.linguaguess.data.local.repository.interfaces.LocalJapaneseWordRepo
import com.example.linguaguess.data.local.repository.interfaces.LocalScoreRepo
import com.example.linguaguess.data.remote.apis.AuthApiService
import com.example.linguaguess.data.remote.apis.ChapterApiService
import com.example.linguaguess.data.remote.apis.CollectionApiService
import com.example.linguaguess.data.remote.apis.JapaneseWordApiService
import com.example.linguaguess.data.remote.mock.MockRemoteAuthRepo
import com.example.linguaguess.data.remote.mock.MockRemoteChapterRepo
import com.example.linguaguess.data.remote.mock.MockRemoteCollectionRepo
import com.example.linguaguess.data.remote.mock.MockRemoteJapaneseWordRepo
import com.example.linguaguess.data.remote.repository.implementations.RemoteAuthRepoImp
import com.example.linguaguess.data.remote.repository.implementations.RemoteChapterRepoImp
import com.example.linguaguess.data.remote.repository.implementations.RemoteCollectionRepoImp
import com.example.linguaguess.data.remote.repository.implementations.RemoteJapaneseWordRepoImp
import com.example.linguaguess.data.remote.repository.interfaces.RemoteAuthRepo
import com.example.linguaguess.data.remote.repository.interfaces.RemoteChapterRepo
import com.example.linguaguess.data.remote.repository.interfaces.RemoteCollectionRepo
import com.example.linguaguess.data.remote.repository.interfaces.RemoteJapaneseWordRepo
import com.example.linguaguess.data.remote.tokenrelated.TokenManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    fun provideLocalChapterRepo(
        chapterDao: ChapterDao
    ): LocalChapterRepo {
        return LocalChapterRepoImp(chapterDao)
    }

    @Provides
    fun provideLocalCollectionRepo(
        collectionDao: CollectionDao
    ): LocalCollectionRepo {
        return LocalCollectionRepoImp(collectionDao)
    }

    @Provides
    fun provideLocalCollectionWithChaptersAndWordsRepo(
        collectionWithChaptersAndWordsDao: CollectionWithChaptersAndWordsDao
    ): LocalCollectionWithChaptersAndWordsRepo {
        return LocalCollectionWithChaptersAndWordsRepoImp(collectionWithChaptersAndWordsDao)
    }

    @Provides
    fun provideLocalJapaneseWordRepo(
        japaneseWordDao: JapaneseWordDao
    ): LocalJapaneseWordRepo {
        return LocalJapaneseWordRepoImp(japaneseWordDao)
    }

    @Provides
    fun provideLocalScoreRepo(
        scoreDao: ScoreDao
    ): LocalScoreRepo {
        return LocalScoreRepoImp(scoreDao)
    }

    @Provides
    fun provideRemoteAuthRepo(
        authApiService: AuthApiService,
        tokenManager: TokenManager
    ): RemoteAuthRepo {
        if (BuildConfig.USE_MOCK_REPO) {
            return MockRemoteAuthRepo()
        }
        return RemoteAuthRepoImp(authApiService, tokenManager)
    }

    @Provides
    fun provideRemoteChapterRepo(
        chapterApiService: ChapterApiService,
        @ApplicationContext context: Context
    ): RemoteChapterRepo {
        if (BuildConfig.USE_MOCK_REPO) {
            return MockRemoteChapterRepo(context)
        }
        return RemoteChapterRepoImp(chapterApiService)
    }

    @Provides
    fun provideRemoteCollectionRepo(
        collectionApiService: CollectionApiService,
        @ApplicationContext context: Context
    ): RemoteCollectionRepo {
        if (BuildConfig.USE_MOCK_REPO) {
            return MockRemoteCollectionRepo(context)
        }
        return RemoteCollectionRepoImp(collectionApiService)
    }

    @Provides
    fun provideRemoteJapaneseWordRepo(
        japaneseWordApiService: JapaneseWordApiService,
        @ApplicationContext context: Context
    ): RemoteJapaneseWordRepo {
        if (BuildConfig.USE_MOCK_REPO) {
            return MockRemoteJapaneseWordRepo(context)
        }
        return RemoteJapaneseWordRepoImp(japaneseWordApiService)
    }

}