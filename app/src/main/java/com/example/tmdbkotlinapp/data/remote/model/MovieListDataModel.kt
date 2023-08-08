package com.example.tmdbkotlinapp.data.remote.model

import com.example.tmdbkotlinapp.domain.models.Movie
import com.google.gson.annotations.SerializedName

data class MovieListDataModel(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val movieList: List<MovieDataModel>,
    @SerializedName("total_pages")
    val totalPages: Int,
) {
    fun toDomain(): List<Movie> {
        val defaultImageUrl = "https://image.tmdb.org/t/p/w500%s"
        return this.movieList.map {
            Movie(
                movieId = it.movieId,
                originalTitle = it.originalTitle,
                genreList = it.genreList,
                overview = it.overview,
                releaseDate = it.releaseDate,
                rating = it.rating,
                popularity = it.popularity,
                posterPath = defaultImageUrl.format(it.posterPath),
            )
        }
    }
}