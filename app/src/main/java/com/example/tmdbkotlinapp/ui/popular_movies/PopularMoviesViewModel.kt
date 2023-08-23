package com.example.tmdbkotlinapp.ui.popular_movies

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.tmdbkotlinapp.domain.repository.MovieRepository
import com.example.tmdbkotlinapp.ui.base.BaseViewModel
import com.example.tmdbkotlinapp.ui.base.Event
import kotlinx.coroutines.launch
import javax.inject.Inject

class PopularMoviesViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    BaseViewModel<PopularUiState, Event>(PopularUiState.Loading) {

    init {
        viewModelScope.launch {
            getMovies()
        }
    }

    private suspend fun getMovies() {
        updateState {
            PopularUiState.Loading
        }
        movieRepository.getPopularMovieList().cachedIn(viewModelScope).collect {
            val pagingData = it
            updateState {
                PopularUiState.Content(pagingData)
            }
        }
    }
}