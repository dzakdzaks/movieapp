package com.dzakdzaks.movieapp.util.extension

import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.dzakdzaks.movieapp.util.base.Progress
import com.dzakdzaks.movieapp.util.base.Result
import com.dzakdzaks.movieapp.util.delegate.FragmentViewBindingDelegate
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

/**
 * ==================================//==================================
 * ==================================//==================================
 * Created on Friday, 30 October 2020 at 5:45 AM.
 * Project Name => MovieApp
 * Package Name => com.dzakdzaks.movieapp.util
 * ==================================//==================================
 * ==================================//==================================
 */

fun <T : Any> Flow<Result<T>>.applyLoading() =
    onStart { emit(Progress(isLoading = true)) }
        .onCompletion { emit(Progress(isLoading = false)) }


inline fun <reified T> Gson.fromJsonWithTypeToken(json: String): T =
    fromJson(json, object : TypeToken<T>() {}.type)

inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
    crossinline bindingInflater: (LayoutInflater) -> T
) =
    lazy(LazyThreadSafetyMode.NONE) {
        bindingInflater.invoke(layoutInflater)
    }

fun <T : ViewBinding> Fragment.viewBinding(viewBindingFactory: (View) -> T) =
    FragmentViewBindingDelegate(this, viewBindingFactory)