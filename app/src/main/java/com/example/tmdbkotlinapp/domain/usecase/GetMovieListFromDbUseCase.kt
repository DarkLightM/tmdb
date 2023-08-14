package com.example.tmdbkotlinapp.domain.usecase

import com.example.tmdbkotlinapp.data.repository.DEFAULT_IMG_URL
import com.example.tmdbkotlinapp.domain.models.Movie
import com.example.tmdbkotlinapp.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieListFromDbUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    suspend fun invoke(): List<Movie> {
        val movieEntity = movieRepository.getMovieListFromDb()
        return movieEntity.map {
            Movie(
                movieId = it.id,
                originalTitle = it.title,
                genreList = it.genre,
                overview = it.overview,
                releaseDate = it.releaseDate,
                rating = it.rating,
                posterPath = DEFAULT_IMG_URL.format(it.posterPath),
                cast = it.actors,
                popularity = 0F,
            )
        }
    }
}