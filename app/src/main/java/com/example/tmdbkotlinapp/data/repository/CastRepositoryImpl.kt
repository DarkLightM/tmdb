package com.example.tmdbkotlinapp.data.repository

import com.example.tmdbkotlinapp.data.MovieService
import com.example.tmdbkotlinapp.domain.base.WorkResult
import com.example.tmdbkotlinapp.domain.base.map
import com.example.tmdbkotlinapp.domain.models.Actor
import com.example.tmdbkotlinapp.domain.repository.CastRepository
import javax.inject.Inject

class CastRepositoryImpl @Inject constructor(private val movieService: MovieService) :
    CastRepository {
    override suspend fun getMovieCast(id: Int): WorkResult<List<Actor>> {
        return movieService.getMovieCast(id).map {
            it.toDomain()
        }
    }
}