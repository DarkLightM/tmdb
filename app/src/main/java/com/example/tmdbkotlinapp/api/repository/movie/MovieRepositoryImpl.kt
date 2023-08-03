package com.example.tmdbkotlinapp.api.repository.movie

import com.example.tmdbkotlinapp.api.MovieService
import com.example.tmdbkotlinapp.models.movie.Movie
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val movieService: MovieService) :
    MovieRepository {

    override suspend fun getRandomMovieList(page: Int, year: Int, genre: String): List<Movie> {
        return movieService.getRandomMovie(page, year, genre).toDomain()
    }

    override suspend fun getPopularMovieList(): List<Movie> {
        return movieService.getPopular().toDomain()
    }

    override suspend fun getMovieDetails(id: Int): Movie {
        return movieService.getMovieDetails(id)
    }

    override suspend fun getTotalPages(year: Int, genre: String): Int {
        return movieService.getRandomMovie(1, year, genre).totalPages
    }
}