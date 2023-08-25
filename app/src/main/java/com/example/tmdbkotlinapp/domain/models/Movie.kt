package com.example.tmdbkotlinapp.domain.models

data class Movie(
    val movieId: Int,
    val movieRemoteId: Int,
    val isAdult: Boolean,
    val originalTitle: String?,
    val genreList: List<Genre>?,
    val overview: String,
    val releaseDate: String,
    val rating: Float,
    val popularity: Float,
    val posterPath: String,
    val cast: List<Actor>?,
)