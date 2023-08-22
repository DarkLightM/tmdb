package com.example.tmdbkotlinapp.ui.movie_details

import androidx.lifecycle.viewModelScope
import com.example.tmdbkotlinapp.domain.repository.MovieRepository
import com.example.tmdbkotlinapp.ui.base.BaseViewModel
import com.example.tmdbkotlinapp.ui.base.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseViewModel<DetailUiState, Event>(DetailUiState.Loading) {

    fun loadMovieDetails(id: Int, remoteId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            updateState {
                DetailUiState.Loading
            }
            val movie = movieRepository.getMovieDetails(id, remoteId)
            updateState {
                DetailUiState.Content(movie)
            }
        }
    }

    fun saveMovieInDb() {
        viewModelScope.launch(Dispatchers.IO) {
            (state.value as? DetailUiState.Content)?.let {
                movieRepository.saveMovieInDb(it.movie)
            }
        }
    }
}