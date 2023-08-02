package com.example.tmdbkotlinapp.api.repository.movie

import com.example.tmdbkotlinapp.models.movie.Movie

interface IMovieRepository {

    suspend fun getRandomMovieList(page: Int, year: Int, genre: String): List<Movie>

    suspend fun getPopularMovieList(): List<Movie>

    suspend fun getMovieDetails(id: Int): Movie

    suspend fun getTotalPages(year: Int, genre: String): Int
}