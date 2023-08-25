package com.example.tmdbkotlinapp.ui.movie_details

import androidx.lifecycle.viewModelScope
import com.example.tmdbkotlinapp.domain.base.handle
import com.example.tmdbkotlinapp.domain.repository.MovieRepository
import com.example.tmdbkotlinapp.ui.base.BaseViewModel
import com.example.tmdbkotlinapp.ui.base.ErrorEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseViewModel<DetailUiState, ErrorEvent>(DetailUiState.Loading) {

    private var lastLoadedMovieId = -1

    fun loadMovieDetails(remoteId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            if (remoteId != lastLoadedMovieId) {
                updateState {
                    DetailUiState.Loading
                }
            }

            val workResult = movieRepository.getMovieDetails(remoteId)

            workResult.handle(onSuccess = { movieResult ->
                lastLoadedMovieId = remoteId
                updateState {
                    DetailUiState.Content(movieResult.movie, movieResult.isSaved)
                }
            }, onNotSuccess = {
                sendEvent(ErrorEvent.SendErrorToast("Error"))
            })

        }
    }

    fun saveMovieInDb() {
        viewModelScope.launch(Dispatchers.IO) {
            (state.value as? DetailUiState.Content)?.let { state ->
                movieRepository.saveMovieInDb(state.movie)
                loadMovieDetails(state.movie.movieRemoteId)
            }
        }
    }

    fun deleteMovieFromDb() {
        viewModelScope.launch(Dispatchers.IO) {
            (state.value as? DetailUiState.Content)?.let { state ->
                movieRepository.deleteMovieFromDb(state.movie.movieRemoteId)
                loadMovieDetails(state.movie.movieRemoteId)
            }
        }
    }
}