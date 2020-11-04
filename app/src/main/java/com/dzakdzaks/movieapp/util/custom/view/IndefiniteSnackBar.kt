package com.dzakdzaks.movieapp.util.custom.view

import android.view.View
import com.dzakdzaks.movieapp.R
import com.google.android.material.snackbar.Snackbar

object IndefiniteSnackBar {

    private var snackBar: Snackbar? = null

    fun show(view: View, text: String, action: () -> Unit) {
        snackBar = Snackbar.make(view, text, Snackbar.LENGTH_INDEFINITE).apply {
            setAction(view.context.getString(R.string.retry)) { action() }
            show()
        }
    }

    fun hide() {
        snackBar?.dismiss()
    }

}