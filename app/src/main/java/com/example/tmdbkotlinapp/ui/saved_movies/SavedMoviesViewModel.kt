package com.example.tmdbkotlinapp.ui.saved_movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbkotlinapp.domain.models.Movie
import com.example.tmdbkotlinapp.domain.usecase.GetMovieListFromDbUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SavedMoviesViewModel @Inject constructor(private val getMovieListFromDbUseCase: GetMovieListFromDbUseCase) :
    ViewModel() {
    private val _movieList = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>> get() = _movieList

    init {
        getSavedMovieList()
    }

    private fun getSavedMovieList() {
        viewModelScope.launch(Dispatchers.IO) {
            _movieList.postValue(getMovieListFromDbUseCase.invoke())
        }
    }
}