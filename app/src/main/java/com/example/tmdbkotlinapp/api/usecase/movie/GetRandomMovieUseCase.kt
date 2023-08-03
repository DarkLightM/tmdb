package com.example.tmdbkotlinapp.api.usecase.movie

import com.example.tmdbkotlinapp.api.repository.movie.MovieRepository
import com.example.tmdbkotlinapp.models.movie.Movie
import javax.inject.Inject

class GetRandomMovieUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    suspend fun invoke(year: Int, genre: String): Movie {
        val totalPages = movieRepository.getTotalPages(year, genre)
        val randomPage = (1..Int.MAX_VALUE).random() % totalPages
        val movieList = movieRepository.getRandomMovieList(randomPage, year, genre)
        return movieList.random()
    }
}