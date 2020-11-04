package com.dzakdzaks.movieapp.util.base

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel

/**
 * ==================================//==================================
 * ==================================//==================================
 * Created on Friday, 30 October 2020 at 6:46 AM.
 * Project Name => MovieApp
 * Package Name => com.dzakdzaks.movieapp.util.base
 * ==================================//==================================
 * ==================================//==================================
 */
abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {

    protected val context: Context = application.applicationContext

}