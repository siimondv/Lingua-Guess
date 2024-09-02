package com.example.linguaguess.di

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.example.linguaguess.data.local.database.CollectionDatabase
import com.example.linguaguess.data.remote.apis.ChapterApiService
import com.example.linguaguess.data.remote.apis.CollectionApiService
import com.example.linguaguess.data.remote.apis.JapaneseWordApiService
import com.example.linguaguess.domain.model.CollectionJ
import com.example.linguaguess.domain.pagingservice.CollectionPagingSource
import com.example.linguaguess.domain.service.GetCollectionsUseCase
import com.example.linguaguess.utils.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO


    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().readTimeout(Constants.TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(Constants.TIMEOUT, TimeUnit.SECONDS).build()
    }

    class LocalDateTimeDeserializer : JsonDeserializer<LocalDateTime> {
        override fun deserialize(
            json: JsonElement, typeOfT: Type, context: JsonDeserializationContext
        ): LocalDateTime {
            return LocalDateTime.parse(json.asString, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        }
    }

    class LocalDateTimeSerializer : JsonSerializer<LocalDateTime> {
        override fun serialize(
            src: LocalDateTime, typeOfSrc: Type, context: JsonSerializationContext
        ): JsonElement {
            return JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
        }
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().registerTypeAdapter(
            LocalDateTime::class.java,
            LocalDateTimeSerializer()
        ).registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeDeserializer()).create()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(gson: Gson): GsonConverterFactory =
        GsonConverterFactory.create(gson)


    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL).client(okHttpClient)
            .addConverterFactory(gsonConverterFactory).build()
    }

    @Singleton
    @Provides
    fun provideCollectionApiService(retrofit: Retrofit): CollectionApiService =
        retrofit.create(CollectionApiService::class.java)

    @Singleton
    @Provides
    fun provideChapterApiService(retrofit: Retrofit): ChapterApiService =
        retrofit.create(ChapterApiService::class.java)

    @Singleton
    @Provides
    fun provideJapaneseWordApiService(retrofit: Retrofit): JapaneseWordApiService =
        retrofit.create(JapaneseWordApiService::class.java)


    @Provides
    @Singleton
    fun provideCollectionPager(getCollectionsUseCase: GetCollectionsUseCase): Pager<Int, CollectionJ> {
        return Pager(config = PagingConfig(
            pageSize = 10,
            initialLoadSize = 10,
            prefetchDistance = 10,
            enablePlaceholders = false
        ), pagingSourceFactory = {
            CollectionPagingSource(getCollectionsUseCase)
        })
    }

    @Provides
    @Singleton
    fun provideCollectionDatabase(@ApplicationContext context: Context): CollectionDatabase {
        return Room.databaseBuilder(
            context,
            CollectionDatabase::class.java,
            "collections.db"
        ).build()
    }
}