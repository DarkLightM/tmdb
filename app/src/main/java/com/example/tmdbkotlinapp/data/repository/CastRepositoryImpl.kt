package com.example.tmdbkotlinapp.data.repository

import com.example.tmdbkotlinapp.domain.repository.CastRepository
import com.example.tmdbkotlinapp.data.MovieService
import com.example.tmdbkotlinapp.domain.models.Actor
import javax.inject.Inject

class CastRepositoryImpl @Inject constructor(private val movieService: MovieService) :
    CastRepository {
    override suspend fun getMovieCast(id: Int): List<Actor> {
        return movieService.getMovieCast(id).toDomain()
    }
}