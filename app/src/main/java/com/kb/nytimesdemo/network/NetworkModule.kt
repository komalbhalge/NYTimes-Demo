package com.kb.nytimes.foundation.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kb.nytimesdemo.data.ArticlesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(noConnectivityInterceptor: NoConnectivityInterceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addInterceptor(noConnectivityInterceptor)
            .addInterceptor(
                HttpLoggingInterceptor().setLevel(
                    HttpLoggingInterceptor.Level.BODY
                )
            )
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .create()
    }

    @Provides
    @Singleton
    fun provideArticleAPI(retrofit: Retrofit): ArticlesApi =
        retrofit.create(ArticlesApi::class.java)

    @Provides
    @Singleton
    fun provideBaseUrl() = "https://api.nytimes.com/svc/"

    @Provides
    @Singleton
    fun provideRetrofit(
        baseUrl: String,
        httpClient: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}