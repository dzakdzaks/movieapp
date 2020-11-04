package com.dzakdzaks.movieapp.data.remote

import com.dzakdzaks.movieapp.data.remote.entity.ResponsePopularMovie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * ==================================//==================================
 * ==================================//==================================
 * Created on Friday, 30 October 2020 at 6:04 AM.
 * Project Name => MovieApp
 * Package Name => com.dzakdzaks.movieapp.data
 * ==================================//==================================
 * ==================================//==================================
 */

interface RemoteService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
            @Query("api_key") apiKey: String = RemoteConfig.apiKey,
            @Query("page") page: Int
    ): Response<ResponsePopularMovie>
}