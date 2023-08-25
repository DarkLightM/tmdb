package com.example.tmdbkotlinapp.data.remote.utils

import java.io.IOException

class NetworkException(e: IOException) : IOException(e)

class NetworkHttpException(code: Int, message: String) : Exception(message)

class UndefinedException(error: Throwable) : Throwable()