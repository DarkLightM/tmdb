package com.example.tmdbkotlinapp.ui.saved_movies

import com.example.tmdbkotlinapp.ui.base.UiState

sealed class SavedUiState : UiState {
    object Loading : SavedUiState()
    object Content : SavedUiState()
}
