package com.example.tmdbkotlinapp.ui.saved_movies

import androidx.lifecycle.viewModelScope
import com.example.tmdbkotlinapp.domain.usecase.GetSavedMoviesUseCase
import com.example.tmdbkotlinapp.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class SavedMoviesViewModel @Inject constructor(private val getSavedMoviesUseCase: GetSavedMoviesUseCase) :
    BaseViewModel<SavedUiState, SavedEvent>(SavedUiState.Loading) {

    init {
        viewModelScope.launch {
            getSavedMovieList()
        }
    }

    private suspend fun getSavedMovieList() {
        getSavedMoviesUseCase().collect{movie ->
            sendEvent(SavedEvent.ShowSavedMovies(movie))
            updateState {
                SavedUiState.Content(movie)
            }
        }

    }
}