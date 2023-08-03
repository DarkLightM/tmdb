package com.example.tmdbkotlinapp.ui.popular_movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbkotlinapp.domain.models.Movie
import com.example.tmdbkotlinapp.domain.repository.MovieRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class PopularMoviesViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {
    private val _popularMovieList = MutableLiveData<List<Movie>>()
    private val page: Int = 1
    val popularMovieList : LiveData<List<Movie>> get() = _popularMovieList

    init{
        getPopularMovieList()
    }

    private fun getPopularMovieList(){
        viewModelScope.launch {
            _popularMovieList.postValue(movieRepository.getPopularMovieList(page))
        }
    }
}