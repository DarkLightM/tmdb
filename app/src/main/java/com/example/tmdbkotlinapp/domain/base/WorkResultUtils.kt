package com.example.tmdbkotlinapp.domain.base

suspend fun <T, E> WorkResult<T>.map(mapper: suspend (data: T) -> E): WorkResult<E> {
    return when (this) {
        is WorkResult.Success -> WorkResult.Success(data = mapper(this.data))
        is WorkResult.Error -> this
        is WorkResult.Fail -> this
        is WorkResult.Empty -> this
    }
}

fun <T : Any> WorkResult<T>.handle(
    onSuccess: (T) -> Unit,
    onNotSuccess: (WorkResult<T>) -> Unit
) {
    when (this) {
        is WorkResult.Success -> onSuccess(this.data)
        else -> onNotSuccess(this)
    }
}