package com.example.tmdbkotlinapp.api.repository.mappers

import com.example.tmdbkotlinapp.models.movie.Movie
import com.example.tmdbkotlinapp.models.movie.MovieListDataModel

class MapToMovieList {
    companion object {
        fun mapToMovieList(
            movieListDataModel: MovieListDataModel,
        ): List<Movie> {
            return movieListDataModel.movieList.map {
                Movie(
                    movieId = it.movieId,
                    originalTitle = it.originalTitle,
                    genreList = it.genreList,
                    overview = it.overview,
                    releaseDate = it.releaseDate,
                    rating = it.rating,
                    posterPath = it.posterPath,
                    cast = null,
                )
            }
        }
    }
}