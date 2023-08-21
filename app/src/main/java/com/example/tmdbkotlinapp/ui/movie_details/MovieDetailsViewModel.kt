package com.example.tmdbkotlinapp.ui.movie_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbkotlinapp.data.repository.DataSource
import com.example.tmdbkotlinapp.domain.models.Actor
import com.example.tmdbkotlinapp.domain.models.Movie
import com.example.tmdbkotlinapp.domain.repository.CastRepository
import com.example.tmdbkotlinapp.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(
    private val castRepository: CastRepository, private val movieRepository: MovieRepository
) : ViewModel() {
    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> get() = _movie

    private val _cast = MutableLiveData<List<Actor>>()
    val cast: LiveData<List<Actor>> get() = _cast

    fun loadMovieDetailsFromServer(id: Int, source: DataSource) {
        viewModelScope.launch(Dispatchers.IO) {
            _movie.postValue(movieRepository.getMovieDetails(id, source))
            _cast.postValue(castRepository.getMovieCast(id))
        }
    }

    fun saveMovieInDb() {
        viewModelScope.launch(Dispatchers.IO) {
            movieRepository.saveMovieInDb(requireNotNull(movie.value), requireNotNull(cast.value))
        }
    }
}