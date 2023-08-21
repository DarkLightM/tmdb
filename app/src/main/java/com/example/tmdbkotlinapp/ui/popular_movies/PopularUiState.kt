package com.example.tmdbkotlinapp.ui.popular_movies

import com.example.tmdbkotlinapp.ui.base.UiState

sealed class PopularUiState : UiState {
    object Loading : PopularUiState()
    object Content : PopularUiState()
}