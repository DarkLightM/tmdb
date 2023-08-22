package com.example.tmdbkotlinapp.ui.random_movie

import com.example.tmdbkotlinapp.domain.models.Movie
import com.example.tmdbkotlinapp.ui.base.Event

sealed class RandomEvent : Event {
    class GoToDetail(val movie: Movie) : RandomEvent()
    class SendErrorToast(val errorText: String): RandomEvent()
}