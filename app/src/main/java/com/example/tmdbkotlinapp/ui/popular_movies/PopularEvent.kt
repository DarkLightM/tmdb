package com.example.tmdbkotlinapp.ui.popular_movies

import androidx.paging.PagingData
import com.example.tmdbkotlinapp.domain.models.Movie
import com.example.tmdbkotlinapp.ui.base.Event

sealed class PopularEvent: Event {
    class ShowMovie(val pagingData: PagingData<Movie>): PopularEvent()
}