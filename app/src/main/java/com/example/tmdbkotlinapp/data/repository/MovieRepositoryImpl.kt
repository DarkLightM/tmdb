package com.example.tmdbkotlinapp.data.repository

import com.example.tmdbkotlinapp.domain.repository.MovieRepository
import com.example.tmdbkotlinapp.data.MovieService
import com.example.tmdbkotlinapp.data.remote.model.MovieDataModel
import com.example.tmdbkotlinapp.domain.models.Movie
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
        return movieService.getMovieDetails(id).toDomain()
    }

    override suspend fun getTotalPages(year: Int, genre: String): Int {
        return movieService.getRandomMovie(1, year, genre).totalPages
    }
}