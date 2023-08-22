package com.example.tmdbkotlinapp.domain.base

sealed class WorkResult<out T> {
    object Empty : WorkResult<Nothing>()

    data class Success<T>(
        val data: T
    ) : WorkResult<T>()

    data class Fail(
        val exception: Throwable? = null
    ) : WorkResult<Nothing>()

    data class Error(
        val exception: Exception,
        val message: String? = null
    ) : WorkResult<Nothing>()
}