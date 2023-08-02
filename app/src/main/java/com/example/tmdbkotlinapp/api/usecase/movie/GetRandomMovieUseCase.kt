package com.example.tmdbkotlinapp.api.usecase.movie

import com.example.tmdbkotlinapp.api.repository.movie.IMovieRepository
import com.example.tmdbkotlinapp.models.movie.Movie
import javax.inject.Inject

class GetRandomMovieUseCase @Inject constructor(private val iMovieRepository: IMovieRepository) :
    IGetRandomMovieUseCase {
    override suspend fun invoke(year: Int, genre: String): Movie {
        val totalPages = iMovieRepository.getTotalPages(year, genre)
        val randomPage = (1..Int.MAX_VALUE).random() % totalPages
        val movieList = iMovieRepository.getRandomMovieList(randomPage, year, genre)
        return movieList.random()
    }
}