package com.example.linguaguess.di


import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.linguaguess.domain.model.CollectionJ
import com.example.linguaguess.domain.pagingservice.CollectionPagingSource
import com.example.linguaguess.domain.service.remote.GetRemoteCollectionsUseCase
import com.example.linguaguess.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun provideCollectionPager(getRemoteCollectionsUseCase: GetRemoteCollectionsUseCase): Pager<Int, CollectionJ> {
        return Pager(config = PagingConfig(
            pageSize = 10,
            initialLoadSize = 10,
            prefetchDistance = 10,
            enablePlaceholders = false
        ), pagingSourceFactory = {
            CollectionPagingSource(getRemoteCollectionsUseCase)
        })
    }



}

