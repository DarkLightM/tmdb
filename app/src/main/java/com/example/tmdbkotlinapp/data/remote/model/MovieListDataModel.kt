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
        return this.movieList.map {
            it.toDomain()
        }
    }
}