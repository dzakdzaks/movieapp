package com.dzakdzaks.movieapp.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

/**
 * ==================================//==================================
 * ==================================//==================================
 * Created on Friday, 30 October 2020 at 6:01 AM.
 * Project Name => MovieApp
 * Package Name => com.dzakdzaks.movieapp.data
 * ==================================//==================================
 * ==================================//==================================
 */

object RemoteConfig {

    const val baseURL = "https://api.themoviedb.org/3/"
    const val apiKey = "a1821872f81f15ac6148ba0e0b625bbd"

    private fun loggingInterceptorClient(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    val httpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptorClient())
            .build()
}