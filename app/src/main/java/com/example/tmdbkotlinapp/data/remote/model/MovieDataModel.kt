package com.example.tmdbkotlinapp.data.remote.model

import com.example.tmdbkotlinapp.data.repository.DEFAULT_IMG_URL
import com.example.tmdbkotlinapp.domain.models.Genre
import com.example.tmdbkotlinapp.domain.models.Movie
import com.example.tmdbkotlinapp.ui.utils.formatFloat
import com.google.gson.annotations.SerializedName

data class MovieDataModel(
    @SerializedName("id")
    val movieId: Int,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("genres")
    val genreList: List<Genre>,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("vote_average")
    val rating: Float,
    @SerializedName("popularity")
    val popularity: Float,
    @SerializedName("poster_path")
    val posterPath: String,
) {
    fun toDomain(): Movie {
        return Movie(
            movieId = this.movieId,
            originalTitle = this.originalTitle,
            genreList = this.genreList,
            overview = this.overview,
            releaseDate = this.releaseDate,
            rating = formatFloat(this.rating),
            popularity = this.popularity,
            posterPath = DEFAULT_IMG_URL.format(this.posterPath),
            cast = null,
        )
    }
}
