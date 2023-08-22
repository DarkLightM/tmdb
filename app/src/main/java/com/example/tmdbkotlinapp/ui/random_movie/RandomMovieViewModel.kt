package com.example.tmdbkotlinapp.ui.random_movie

import androidx.lifecycle.viewModelScope
import com.example.tmdbkotlinapp.domain.usecase.GetRandomMovieUseCase
import com.example.tmdbkotlinapp.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class RandomMovieViewModel @Inject constructor(private val getRandomMovieUseCase: GetRandomMovieUseCase) :
    BaseViewModel<RandomUiState, RandomEvent>(RandomUiState.Content) {
    fun getRandomMovie(year: Int, genre: String) {
        updateState {
            RandomUiState.Loading
        }
        viewModelScope.launch {
            val movie = getRandomMovieUseCase(year, genre)
            if (movie == null)
                sendEvent(RandomEvent.SendErrorToast("Произошла ошибка при загрузке фильма"))
            else
                sendEvent(RandomEvent.GoToDetail(movie))
            updateState {
                RandomUiState.Content
            }
        }
    }
}