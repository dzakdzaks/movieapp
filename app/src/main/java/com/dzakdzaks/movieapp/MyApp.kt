package com.dzakdzaks.movieapp

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * ==================================//==================================
 * ==================================//==================================
 * Created on Friday, 30 October 2020 at 5:25 AM.
 * Project Name => MovieApp
 * Package Name => com.dzakdzaks.movieapp
 * ==================================//==================================
 * ==================================//==================================
 */

@HiltAndroidApp
class MyApp : Application() {

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }

    companion object {
        private var instance: MyApp? = null

        fun context(): Context {
            return instance!!.applicationContext
        }
    }

}