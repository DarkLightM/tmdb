package com.example.tmdbkotlinapp.ui.random_movie

import com.example.tmdbkotlinapp.ui.base.Event
import com.example.tmdbkotlinapp.ui.base.UiState

sealed class RandomUiState : UiState {
    object Loading : RandomUiState()
    object Content : RandomUiState()
}

sealed class RandomEvent : Event {
    class GoToDetail(val movieId: Int) : RandomEvent()
}