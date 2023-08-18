package com.example.tmdbkotlinapp.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

abstract class BaseViewModel<S: UiState, E: Event> (initialState: S) : ViewModel() {
    private val _state: MutableStateFlow<S> = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    private val _events: Channel<E> = Channel(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    protected fun updateState(updater: (S) -> S){
        _state.value = updater(_state.value)
    }

    protected fun sendEvent(event: E){
        _events.trySend(event)
    }
}