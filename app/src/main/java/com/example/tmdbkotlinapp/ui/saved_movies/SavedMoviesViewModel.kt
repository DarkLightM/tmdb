package com.example.tmdbkotlinapp.ui.saved_movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbkotlinapp.domain.models.Movie
import com.example.tmdbkotlinapp.domain.usecase.GetSavedMoviesUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class SavedMoviesViewModel @Inject constructor(private val getSavedMoviesUseCase: GetSavedMoviesUseCase) :
    ViewModel() {
    private val _movieList = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>> get() = _movieList

    init {
        viewModelScope.launch {
            getSavedMovieList()
        }
    }

    private suspend fun getSavedMovieList() {
        getSavedMoviesUseCase().collect{
            _movieList.postValue(it)
        }
    }
}