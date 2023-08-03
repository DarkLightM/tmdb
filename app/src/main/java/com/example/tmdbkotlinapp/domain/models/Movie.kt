package com.example.tmdbkotlinapp.domain.models

import com.google.gson.annotations.SerializedName

data class Movie(
    val movieId: Int,
    val originalTitle: String?,
    val genreList: List<Int>,
    val overview: String,
    val releaseDate: String,
    val rating: Float,
    val popularity: Float,
    val posterPath: String,
    val cast: List<Actor>?,
)