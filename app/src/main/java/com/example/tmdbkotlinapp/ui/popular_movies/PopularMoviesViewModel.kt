package com.example.tmdbkotlinapp.ui.popular_movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.tmdbkotlinapp.domain.models.Movie
import com.example.tmdbkotlinapp.domain.repository.MovieRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class PopularMoviesViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {
    private val _popularMovieList = MutableLiveData<PagingData<Movie>>()
    val popularMovieList: LiveData<PagingData<Movie>> get() = _popularMovieList

    init {
        viewModelScope.launch {
            getMovies()
        }
    }

    private suspend fun getMovies() {
        movieRepository.getPopularMovieList().collect {
            _popularMovieList.postValue(it)
        }
    }
}