package com.example.tmdbkotlinapp.domain.repository

import com.example.tmdbkotlinapp.data.remote.model.MovieDataModel
import com.example.tmdbkotlinapp.domain.models.Movie

interface MovieRepository {

    suspend fun getRandomMovieList(page: Int, year: Int, genre: String): List<Movie>

    suspend fun getPopularMovieList(page: Int): List<Movie>

    suspend fun getMovieDetails(id: Int): Movie

    suspend fun getTotalPages(year: Int, genre: String): Int
}