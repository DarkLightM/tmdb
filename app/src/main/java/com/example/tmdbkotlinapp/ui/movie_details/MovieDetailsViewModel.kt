package com.example.tmdbkotlinapp.ui.movie_details

import androidx.lifecycle.viewModelScope
import com.example.tmdbkotlinapp.data.remote.utils.NetworkException
import com.example.tmdbkotlinapp.domain.base.WorkResult
import com.example.tmdbkotlinapp.domain.base.handle
import com.example.tmdbkotlinapp.domain.models.MovieResult
import com.example.tmdbkotlinapp.domain.repository.MovieRepository
import com.example.tmdbkotlinapp.ui.base.BaseViewModel
import com.example.tmdbkotlinapp.ui.base.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseViewModel<DetailUiState, Event>(DetailUiState.Loading) {

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
                handleErrorResult(it)
            })

        }
    }

    private fun handleErrorResult(workResult: WorkResult<MovieResult>) {
        when (workResult) {
            is WorkResult.Fail -> {
                if (workResult.exception is NetworkException) {
                    updateState {
                        DetailUiState.NetworkError
                    }
                } else {
                    updateState {
                        DetailUiState.Error
                    }
                }
            }
            else -> {
                updateState {
                    DetailUiState.Error
                }
            }
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