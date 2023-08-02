package com.example.tmdbkotlinapp.api.repository.cast

import com.example.tmdbkotlinapp.api.MovieService
import com.example.tmdbkotlinapp.api.repository.mappers.MapToCast
import com.example.tmdbkotlinapp.models.actor.Actor
import javax.inject.Inject

class CastRepository @Inject constructor(private val movieService: MovieService): ICastRepository {
    override suspend fun getMovieCast(id: Int): List<Actor> {
        return MapToCast.mapToCast(movieService.getMovieCast(id, MovieService.BEARER_TOKEN))
    }
}