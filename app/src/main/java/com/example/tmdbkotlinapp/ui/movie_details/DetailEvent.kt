package com.example.tmdbkotlinapp.ui.movie_details

import com.example.tmdbkotlinapp.domain.models.Actor
import com.example.tmdbkotlinapp.domain.models.Movie
import com.example.tmdbkotlinapp.ui.base.Event


sealed class DetailEvent: Event {
    class ShowDetails(val movie: Movie, val cast: List<Actor>): DetailEvent()
}
