package com.example.tmdbkotlinapp.ui.movie_details

import androidx.lifecycle.viewModelScope
import com.example.tmdbkotlinapp.data.repository.DataSource
import com.example.tmdbkotlinapp.domain.repository.CastRepository
import com.example.tmdbkotlinapp.domain.repository.MovieRepository
import com.example.tmdbkotlinapp.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(
    private val castRepository: CastRepository, private val movieRepository: MovieRepository
) :  BaseViewModel<DetailUiState, DetailEvent>(DetailUiState.Loading) {

    fun loadMovieDetails(id: Int, source: DataSource) {
        viewModelScope.launch(Dispatchers.IO) {
            val movie = movieRepository.getMovieDetails(id, source)
            val cast = castRepository.getMovieCast(id)
            sendEvent(DetailEvent.ShowDetails(movie, cast))
            updateState {
                DetailUiState.Content
            }
        }
    }

    /*fun saveMovieInDb(movie: Movie, cast: List<Actor>) {
        viewModelScope.launch(Dispatchers.IO) {
            movieRepository.saveMovieInDb(movie, cast)
        }
    }*/
}