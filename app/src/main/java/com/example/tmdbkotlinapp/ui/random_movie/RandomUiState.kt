package com.example.tmdbkotlinapp.ui.random_movie

import com.example.tmdbkotlinapp.ui.base.UiState

sealed class RandomUiState : UiState {
    object Loading : RandomUiState()
    object Content : RandomUiState()
}