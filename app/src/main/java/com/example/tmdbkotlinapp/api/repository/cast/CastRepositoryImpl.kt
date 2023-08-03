package com.example.tmdbkotlinapp.api.repository.cast

import com.example.tmdbkotlinapp.api.MovieService
import com.example.tmdbkotlinapp.models.actor.Actor
import javax.inject.Inject

class CastRepositoryImpl @Inject constructor(private val movieService: MovieService): CastRepository {
    override suspend fun getMovieCast(id: Int): List<Actor> {
        return movieService.getMovieCast(id, MovieService.BEARER_TOKEN).toDomain()
    }
}