package com.example.tmdbkotlinapp.ui.movie_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val _isMovieInDb = MutableLiveData<Boolean>()
    val isMovieInDb: LiveData<Boolean> get() = _isMovieInDb

    fun loadMovieDetails(id: Int, remoteId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            updateState {
                DetailUiState.Loading
            }

            val workResult = movieRepository.getMovieDetails(id, remoteId)

            workResult.handle(onSuccess = { movie ->
                updateState {
                    DetailUiState.Content(movie)
                }
            }, onNotSuccess = {
                sendEvent(ErrorEvent.SendErrorToast("Error"))
            })

        }
    }

    fun saveMovieInDb() {
        viewModelScope.launch(Dispatchers.IO) {
            (state.value as? DetailUiState.Content)?.let {
                movieRepository.saveMovieInDb(it.movie)
            }
        }
    }

    fun deleteMovieFromDb() {
        viewModelScope.launch(Dispatchers.IO) {
            (state.value as? DetailUiState.Content)?.let {
                movieRepository.deleteMovieFromDb(it.movie.movieRemoteId)
            }
        }
    }

    fun checkMovieInDb() {
        viewModelScope.launch(Dispatchers.IO) {
            (state.value as? DetailUiState.Content)?.let { content ->
                movieRepository.isMovieInDb(content.movie.movieRemoteId).collect {
                    _isMovieInDb.postValue(it > 0)
                }
            }
        }
    }
}