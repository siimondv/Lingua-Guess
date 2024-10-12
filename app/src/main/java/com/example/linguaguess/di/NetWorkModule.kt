package com.example.linguaguess.di

import android.content.Context
import com.example.linguaguess.BuildConfig
import com.example.linguaguess.data.remote.apis.AuthApiService
import com.example.linguaguess.data.remote.apis.ChapterApiService
import com.example.linguaguess.data.remote.apis.CollectionApiService
import com.example.linguaguess.data.remote.apis.JapaneseWordApiService
import com.example.linguaguess.data.remote.tokenrelated.AuthAuthenticator
import com.example.linguaguess.data.remote.tokenrelated.AuthInterceptor
import com.example.linguaguess.data.remote.tokenrelated.TokenManager
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
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetWorkModule {
    @Named("collection")
    @Singleton
    @Provides
    fun provideHttpClientAuth(
        authInterceptor: AuthInterceptor,
        authAuthenticator: AuthAuthenticator
    ): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(authInterceptor)
            .authenticator(authAuthenticator).readTimeout(Constants.TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(Constants.TIMEOUT, TimeUnit.SECONDS).build()
    }

    @Named("auth")
    @Singleton
    @Provides
    fun provideHttpClientCollection     (
    ): OkHttpClient {
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
    @Named("collectionRetrofit")
    fun provideRetrofitCollections(
        @Named("collection")okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder().baseUrl(BuildConfig.COLLECTION_BASE_URL).client(okHttpClient)
            .addConverterFactory(gsonConverterFactory).build()
    }

    @Singleton
    @Provides
    @Named("authRetrofit")
    fun provideRetrofitAuth(
        @Named("auth")okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder().baseUrl(BuildConfig.AUTH_BASE_URL).client(okHttpClient)
            .addConverterFactory(gsonConverterFactory).build()
    }

    @Singleton
    @Provides
    fun provideCollectionApiService(@Named("collectionRetrofit") retrofit: Retrofit): CollectionApiService =
        retrofit.create(CollectionApiService::class.java)

    @Singleton
    @Provides
    fun provideChapterApiService(@Named("collectionRetrofit") retrofit: Retrofit): ChapterApiService =
        retrofit.create(ChapterApiService::class.java)

    @Singleton
    @Provides
    fun provideJapaneseWordApiService(@Named("collectionRetrofit") retrofit: Retrofit): JapaneseWordApiService =
        retrofit.create(JapaneseWordApiService::class.java)

    @Singleton
    @Provides
    fun provideAuthApiService(@Named("authRetrofit") retrofit: Retrofit): AuthApiService =
        retrofit.create(AuthApiService::class.java)

    @Singleton
    @Provides
    fun provideTokenManager(@ApplicationContext context: Context): TokenManager =
        TokenManager(context)

}