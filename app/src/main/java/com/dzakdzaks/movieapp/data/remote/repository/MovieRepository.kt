package com.dzakdzaks.movieapp.data.remote.repository

import com.dzakdzaks.movieapp.data.remote.RemoteService
import com.dzakdzaks.movieapp.util.constant.Constant
import com.dzakdzaks.movieapp.util.base.Error
import com.dzakdzaks.movieapp.util.base.Success
import com.dzakdzaks.movieapp.util.custom.error.ErrorHandler
import com.invent.bmmerchant.util.custom.error.ErrorResponse
import com.invent.bmmerchant.util.custom.error.NoResponseException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

/**
 * ==================================//==================================
 * ==================================//==================================
 * Created on Friday, 30 October 2020 at 6:13 AM.
 * Project Name => MovieApp
 * Package Name => com.dzakdzaks.movieapp.data
 * ==================================//==================================
 * ==================================//==================================
 */
class MovieRepository @Inject constructor(
    private val remoteService: RemoteService
) {

    private fun <T> responseDecision(response: Response<T>) =
        if (response.isSuccessful && response.body() != null) {
            Success(response.body()!!)
        } else {
            Error(NoResponseException(ErrorHandler.parseError<ErrorResponse>(response.errorBody())?.message))
        }


    suspend fun getPopularMovies(page: Int) =
        if (Constant.isNetworkConnected())
            remoteService.getPopularMovies(page = page).run { responseDecision(this) }
        else
            Error(IOException())

}