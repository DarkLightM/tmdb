package com.example.tmdbkotlinapp.api.usecase.movie

import com.example.tmdbkotlinapp.models.movie.Movie

interface IGetRandomMovieUseCase {
    suspend operator fun invoke(year: Int, genre: String): Movie
}