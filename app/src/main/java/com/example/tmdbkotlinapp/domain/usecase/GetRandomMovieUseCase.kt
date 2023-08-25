package com.example.tmdbkotlinapp.domain.usecase

import com.example.tmdbkotlinapp.domain.base.WorkResult
import com.example.tmdbkotlinapp.domain.models.Movie
import com.example.tmdbkotlinapp.domain.repository.MovieRepository
import javax.inject.Inject

class GetRandomMovieUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(year: Int, genre: String): WorkResult<List<Movie>> {
        val randomPage = (1..Int.MAX_VALUE).random() % 500
        return movieRepository.getRandomMovieList(randomPage, year, genre)
    }
}