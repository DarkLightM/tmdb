package com.example.tmdbkotlinapp.data.remote.model

import com.example.tmdbkotlinapp.domain.models.Actor
import com.example.tmdbkotlinapp.domain.models.Movie
import com.google.gson.annotations.SerializedName

data class MovieDataModel(
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
) {
    fun toDomain(): Movie {
        return Movie(
            this.movieId,
            this.originalTitle,
            this.genreList,
            this.overview,
            this.releaseDate,
            this.rating,
            this.posterPath,
            this.cast
        )
    }
}
