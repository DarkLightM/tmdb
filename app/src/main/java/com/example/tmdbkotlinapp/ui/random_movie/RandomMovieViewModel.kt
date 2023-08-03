package com.example.tmdbkotlinapp.ui.random_movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbkotlinapp.api.usecase.movie.GetRandomMovieUseCase
import com.example.tmdbkotlinapp.models.movie.Movie
import kotlinx.coroutines.launch
import javax.inject.Inject

class RandomMovieViewModel @Inject constructor(private val getRandomMovieUseCase: GetRandomMovieUseCase) :
    ViewModel() {
    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> get() = _movie

    fun getRandomMovie(year: Int, genre: String){
        viewModelScope.launch {
            _movie.postValue(getRandomMovieUseCase.invoke(year, genre))
        }
    }
}