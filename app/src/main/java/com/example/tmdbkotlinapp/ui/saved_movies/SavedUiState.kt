package com.example.tmdbkotlinapp.ui.saved_movies

import com.example.tmdbkotlinapp.domain.models.Movie
import com.example.tmdbkotlinapp.ui.base.UiState

sealed class SavedUiState : UiState {
    object Loading : SavedUiState()
    class Content(val savedMovies: List<Movie>) : SavedUiState()
}
