package com.example.tmdbkotlinapp.domain.usecase

import com.example.tmdbkotlinapp.domain.repository.MovieRepository
import com.example.tmdbkotlinapp.domain.models.Movie
import javax.inject.Inject

class GetRandomMovieUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    suspend fun invoke(year: Int, genre: String): Movie {
        val totalPages = movieRepository.getTotalPages(year, genre)
        val randomPage = (1..Int.MAX_VALUE).random() % totalPages
        val movieList = movieRepository.getRandomMovieList(randomPage, year, genre)
        return movieList.random()
    }
}