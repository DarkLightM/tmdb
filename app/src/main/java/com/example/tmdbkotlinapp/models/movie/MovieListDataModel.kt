package com.example.tmdbkotlinapp.models.movie

import com.google.gson.annotations.SerializedName

data class MovieListDataModel(
    val page: Int,
    @SerializedName("results")
    val movieList: List<Movie>,
    @SerializedName("total_pages")
    val totalPages: Int,
)