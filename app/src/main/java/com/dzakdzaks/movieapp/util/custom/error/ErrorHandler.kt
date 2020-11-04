package com.dzakdzaks.movieapp.util.custom.error

import android.content.Context
import android.view.View
import android.widget.Toast
import com.dzakdzaks.movieapp.util.base.Error
import com.dzakdzaks.movieapp.util.custom.view.IndefiniteSnackBar
import com.invent.bmmerchant.util.custom.error.NoDataException
import com.invent.bmmerchant.util.custom.error.NoResponseException
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import okhttp3.ResponseBody
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException


object ErrorHandler {

    private const val NETWORK_ERROR_MESSAGE =
        "Anda sedang offline, nyalakan koneksi internet untuk kembali online."
    private const val EMPTY_RESPONSE = "Server returned empty response."
    const val NO_SUCH_DATA = "Data not found in the database."
    const val UNKNOWN_ERROR = "Telah terjadi kesalahan, silahkan coba beberapa saat lagi."

    fun handleError(
        view: View,
        throwable: Error,
        shouldToast: Boolean = false,
        shouldShowSnackBar: Boolean = false,
        refreshAction: () -> Unit = {},
        onError: (String) -> Unit = {}
    ) {
        var msg: String
        when (throwable.exception) {
            is IOException -> {
                Timber.e(NETWORK_ERROR_MESSAGE)
                msg = NETWORK_ERROR_MESSAGE
            }
            is HttpException -> {
                Timber.e(
                    "HTTP Exception: ${throwable.exception.code()}"
                )
                msg = "HTTP Exception: ${throwable.exception.code()}"
            }
            is NoResponseException -> {
                Timber.e(EMPTY_RESPONSE)
                msg = EMPTY_RESPONSE
            }
            is NoDataException -> {
                Timber.e(NO_SUCH_DATA)
                msg = NO_SUCH_DATA
            }
            else -> {
                Timber.e(throwable.message)
                msg = throwable.message
            }
        }

        if (shouldShowSnackBar) {
            showSnackBar(view, message = msg, refresh = refreshAction)
        } else {
            if (shouldToast) {
                showLongToast(view.context, msg)
            }
        }

        onError.invoke(msg)
    }

    private fun showSnackBar(view: View, message: String, refresh: () -> Unit = {}) {
        IndefiniteSnackBar.show(view, message, refresh)
    }

    private fun showLongToast(context: Context, message: String) = Toast.makeText(
        context,
        message,
        Toast.LENGTH_LONG
    ).show()

    inline fun <reified T> parseError(responseBody: ResponseBody?): T? {
        val parser = Moshi.Builder().build().adapter(T::class.java)
        val response = responseBody?.string()
        if (response != null)
            try {
                return parser.fromJson(response)
            } catch (e: JsonDataException) {
                e.printStackTrace()
            }
        return null
    }

}