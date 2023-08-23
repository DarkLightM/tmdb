package com.example.tmdbkotlinapp.domain.usecase

import com.example.tmdbkotlinapp.domain.models.Movie
import com.example.tmdbkotlinapp.domain.repository.MovieRepository
import javax.inject.Inject

class GetRandomMovieUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(year: Int, genre: String): Movie? {
        val randomPage = (1..Int.MAX_VALUE).random() % 500
        val movieList = movieRepository.getRandomMovieList(randomPage, year, genre)
        return if (movieList.isEmpty())
            null
        else
            movieList.random()
    }
}