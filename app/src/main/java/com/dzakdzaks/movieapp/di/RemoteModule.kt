package com.dzakdzaks.movieapp.di

import com.dzakdzaks.movieapp.data.remote.RemoteConfig
import com.dzakdzaks.movieapp.data.remote.RemoteService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * ==================================//==================================
 * ==================================//==================================
 * Created on Friday, 30 October 2020 at 5:35 AM.
 * Project Name => MovieApp
 * Package Name => com.dzakdzaks.movieapp.di
 * ==================================//==================================
 * ==================================//==================================
 */
@Module
@InstallIn(ApplicationComponent::class)
object RemoteModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
            .baseUrl(RemoteConfig.baseURL)
            .client(RemoteConfig.httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides
    fun provideGSON(): Gson = GsonBuilder().create()

    @Provides
    fun provideApiService(retrofit: Retrofit): RemoteService =
            retrofit.create(RemoteService::class.java)
}