package com.example.tmdbkotlinapp.ui.random_movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbkotlinapp.domain.usecase.GetRandomMovieUseCase
import com.example.tmdbkotlinapp.domain.models.Movie
import kotlinx.coroutines.launch
import javax.inject.Inject

class RandomMovieViewModel @Inject constructor(private val getRandomMovieUseCase: GetRandomMovieUseCase) :
    ViewModel() {
    private val _movieDataModel = MutableLiveData<Movie>()
    val movieDataModel: LiveData<Movie> get() = _movieDataModel

    fun getRandomMovie(year: Int, genre: String){
        viewModelScope.launch {
            _movieDataModel.postValue(getRandomMovieUseCase.invoke(year, genre))
        }
    }
}