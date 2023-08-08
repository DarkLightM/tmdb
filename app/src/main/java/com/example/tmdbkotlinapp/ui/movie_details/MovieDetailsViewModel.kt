package com.example.tmdbkotlinapp.ui.movie_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbkotlinapp.domain.models.Actor
import com.example.tmdbkotlinapp.domain.models.Movie
import com.example.tmdbkotlinapp.domain.repository.CastRepository
import com.example.tmdbkotlinapp.domain.repository.MovieRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(
    private val castRepository: CastRepository,
    private val movieRepository: MovieRepository
) : ViewModel() {
    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> get() = _movie

    private val _cast = MutableLiveData<List<Actor>>()
    val cast: LiveData<List<Actor>> get() = _cast


    fun getMovieDetails(id: Int){
        viewModelScope.launch {
            _movie.postValue(movieRepository.getMovieDetails(id))
        }
    }

    fun getMovieCast(id: Int){
        viewModelScope.launch {
            _cast.postValue(castRepository.getMovieCast(id))
        }
    }

}