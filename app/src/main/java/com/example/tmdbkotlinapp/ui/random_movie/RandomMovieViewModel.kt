package com.example.tmdbkotlinapp.ui.random_movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbkotlinapp.api.repository.movie.IMovieRepository
import com.example.tmdbkotlinapp.api.usecase.movie.IGetRandomMovieUseCase
import com.example.tmdbkotlinapp.models.movie.Movie
import kotlinx.coroutines.launch
import javax.inject.Inject

class RandomMovieViewModel @Inject constructor(private val iGetRandomMovieUseCase: IGetRandomMovieUseCase) :
    ViewModel() {
    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> get() = _movie

    fun getRandomMovie(year: Int, genre: String){
        viewModelScope.launch {
            _movie.postValue(iGetRandomMovieUseCase.invoke(year, genre))
        }
    }
}