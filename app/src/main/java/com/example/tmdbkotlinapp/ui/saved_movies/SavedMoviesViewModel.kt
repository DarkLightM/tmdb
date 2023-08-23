package com.example.tmdbkotlinapp.ui.saved_movies

import androidx.lifecycle.viewModelScope
import com.example.tmdbkotlinapp.domain.usecase.GetSavedMoviesUseCase
import com.example.tmdbkotlinapp.ui.base.BaseViewModel
import com.example.tmdbkotlinapp.ui.base.Event
import kotlinx.coroutines.launch
import javax.inject.Inject

class SavedMoviesViewModel @Inject constructor(private val getSavedMoviesUseCase: GetSavedMoviesUseCase) :
    BaseViewModel<SavedUiState, Event>(SavedUiState.Loading) {

    init {
        viewModelScope.launch {
            getSavedMovieList()
        }
    }

    private suspend fun getSavedMovieList() {
        updateState {
            SavedUiState.Loading
        }
        getSavedMoviesUseCase().collect{movie ->
            updateState {
                SavedUiState.Content(movie)
            }
        }

    }
}