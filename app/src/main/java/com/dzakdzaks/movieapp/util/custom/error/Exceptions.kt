package com.invent.bmmerchant.util.custom.error

import com.dzakdzaks.movieapp.util.custom.error.ErrorHandler

class NoResponseException(message: String? = ErrorHandler.UNKNOWN_ERROR) : Exception(message)

class NoDataException(message: String? = ErrorHandler.NO_SUCH_DATA) : Exception()