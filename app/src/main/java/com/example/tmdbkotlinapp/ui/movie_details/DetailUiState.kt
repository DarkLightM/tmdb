package com.example.tmdbkotlinapp.ui.movie_details

import com.example.tmdbkotlinapp.domain.models.Movie
import com.example.tmdbkotlinapp.ui.base.UiState

sealed class DetailUiState : UiState {
    object Loading : DetailUiState()
    data class Content (val movie: Movie, val isSaved: Boolean) : DetailUiState()

    object NetworkError: DetailUiState()

    object Error: DetailUiState()
}
