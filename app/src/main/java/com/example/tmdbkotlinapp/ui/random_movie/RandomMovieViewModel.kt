package com.example.tmdbkotlinapp.ui.random_movie

import androidx.lifecycle.viewModelScope
import com.example.tmdbkotlinapp.domain.base.handle
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
            val workResult = getRandomMovieUseCase(year, genre)

            workResult.handle(
                onSuccess = { movies -> sendEvent(RandomEvent.GoToDetail(movies.random())) },
                onNotSuccess = {
                    sendEvent(RandomEvent.SendErrorToast("Произошла ошибка при загрузке фильма"))
                })

            updateState {
                RandomUiState.Content
            }
        }
    }
}