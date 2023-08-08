package com.example.tmdbkotlinapp.ui.popular_movies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.tmdbkotlinapp.domain.models.Movie
import com.example.tmdbkotlinapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class PopularMoviesViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {
    private val _popularMovieList = MutableLiveData<PagingData<Movie>>()
    val popularMovieList: LiveData<PagingData<Movie>> get() = _popularMovieList

    /*init {
        getMovies()
    }*/

   /* private fun getMovies() {
        viewModelScope.launch {
            movieRepository.getPopularMovieList().asFlow().collect{
                Log.i("ViewModel", it.toString())
            }
        }
        //_popularMovieList.postValue(movieRepository.getPopularMovieList().value)
    }*/
}