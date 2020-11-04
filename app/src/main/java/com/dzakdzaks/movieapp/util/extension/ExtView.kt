package com.dzakdzaks.movieapp.util.extension

import android.animation.LayoutTransition
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.dzakdzaks.movieapp.util.constant.Constant

/**
 * ==================================//==================================
 * ==================================//==================================
 * Created on Friday, 30 October 2020 at 6:43 AM.
 * Project Name => MovieApp
 * Package Name => com.dzakdzaks.movieapp.util.extension
 * ==================================//==================================
 * ==================================//==================================
 */
fun ViewGroup.animateLayoutChanges() {
    layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
}

fun View.toggleVisibility(
    isLoading: Boolean,
    shouldHide: Boolean = false,
    reverse: Boolean = false
) {
    this.visibility = Constant.toggleVisibility(isLoading, shouldHide, reverse)
}

fun View.clickableOrNot(isLoading: Boolean) {
    this.isClickable = !isLoading
}

fun View.visible() {
    if (!this.isVisible)
        this.visibility = View.VISIBLE
}

fun View.gone() {
    if (this.isVisible)
        this.visibility = View.GONE
}

fun View.invisible() {
    if (this.isVisible)
        this.visibility = View.INVISIBLE
}

fun View.enable() {
    if (!this.isEnabled)
        this.isEnabled = true
}

fun View.disable() {
    if (this.isEnabled)
        this.isEnabled = false
}

fun View.clickable() {
    if (!this.isClickable)
        this.isClickable = true
}

fun View.notClickable() {
    if (this.isClickable)
        this.isClickable = false
}
