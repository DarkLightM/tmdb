package com.example.tmdbkotlinapp.ui.random_movie

import com.example.tmdbkotlinapp.ui.base.Event

sealed class RandomEvent : Event {
    class GoToDetail(val movieId: Int) : RandomEvent()
}