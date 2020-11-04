package com.dzakdzaks.movieapp.ui

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.asLiveData
import com.dzakdzaks.movieapp.data.remote.repository.MovieRepository
import com.dzakdzaks.movieapp.util.base.BaseViewModel
import com.dzakdzaks.movieapp.util.base.Error
import com.dzakdzaks.movieapp.util.extension.applyLoading
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * ==================================//==================================
 * ==================================//==================================
 * Created on Friday, 30 October 2020 at 6:45 AM.
 * Project Name => MovieApp
 * Package Name => com.dzakdzaks.movieapp.ui
 * ==================================//==================================
 * ==================================//==================================
 */

class MainViewModel @ViewModelInject constructor(
    application: Application,
    private val movieRepository: MovieRepository
) : BaseViewModel(application) {

    fun getPopularMovies(page: Int) =
        flow { emit(movieRepository.getPopularMovies(page)) }
            .applyLoading()
            .catch { emit(Error(it)) }
            .flowOn(Dispatchers.IO).asLiveData()

}