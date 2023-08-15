package com.example.tmdbkotlinapp.domain.usecase

import com.example.tmdbkotlinapp.data.repository.DEFAULT_IMG_URL
import com.example.tmdbkotlinapp.domain.models.Movie
import com.example.tmdbkotlinapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

import javax.inject.Inject

class GetSavedMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(): Flow<List<Movie>> {
        return movieRepository.getSavedMovies().map { movieEntities ->
            movieEntities.map { movieEntity ->
                Movie(
                    movieId = movieEntity.id,
                    originalTitle = movieEntity.title,
                    genreList = movieEntity.genre,
                    overview = movieEntity.overview,
                    releaseDate = movieEntity.releaseDate,
                    rating = movieEntity.rating,
                    posterPath = DEFAULT_IMG_URL.format(movieEntity.posterPath),
                    cast = movieEntity.actors,
                    popularity = 0F
                )
            }
        }
    }
}