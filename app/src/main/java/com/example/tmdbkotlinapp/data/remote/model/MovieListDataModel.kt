package com.example.tmdbkotlinapp.data.remote.model

import android.util.Log
import com.example.tmdbkotlinapp.domain.models.Movie
import com.google.gson.annotations.SerializedName

data class MovieListDataModel(
    val page: Int,
    @SerializedName("results")
    val movieList: List<MovieDataModel>,
    @SerializedName("total_pages")
    val totalPages: Int,
) {
    fun toDomain(): List<Movie> {
        Log.i("Popular movie list", this.movieList.toString())
        return this.movieList.map {
            Movie(
                movieId = it.movieId,
                originalTitle = it.originalTitle,
                genreList = it.genreList,
                overview = it.overview,
                releaseDate = it.releaseDate,
                rating = it.rating,
                popularity = it.popularity,
                posterPath = it.posterPath,
                cast = null,
            )
        }
    }
}