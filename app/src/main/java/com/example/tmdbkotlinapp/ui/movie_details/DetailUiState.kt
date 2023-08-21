package com.example.tmdbkotlinapp.ui.movie_details

import com.example.tmdbkotlinapp.ui.base.UiState

sealed class DetailUiState : UiState {
    object Loading : DetailUiState()
    object Content : DetailUiState()
}
