package com.example.tmdbkotlinapp.ui.saved_movies

import com.example.tmdbkotlinapp.domain.models.Movie
import com.example.tmdbkotlinapp.ui.base.Event

sealed class SavedEvent: Event {
    class ShowSavedMovies(val movies: List<Movie>): SavedEvent()
}
