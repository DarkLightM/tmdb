package com.example.tmdbkotlinapp.models.movie

import com.example.tmdbkotlinapp.models.actor.Actor
import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id")
    val movieId: Int,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("genre_ids")
    val genreList: List<Int>,
    val overview: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("vote_average")
    val rating: Float,
    @SerializedName("poster_path")
    val posterPath: String,
    val cast: List<Actor>?,

    )
