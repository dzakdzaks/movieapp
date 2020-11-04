package com.dzakdzaks.movieapp.di

import com.dzakdzaks.movieapp.data.remote.RemoteService
import com.dzakdzaks.movieapp.data.remote.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

/**
 * ==================================//==================================
 * ==================================//==================================
 * Created on Friday, 30 October 2020 at 5:32 AM.
 * Project Name => MovieApp
 * Package Name => com.dzakdzaks.movieapp.di
 * ==================================//==================================
 * ==================================//==================================
 */

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMovieRepository(
            remoteService: RemoteService
    ) =
            MovieRepository(remoteService)

}