package com.example.tmdbkotlinapp.domain.repository

import com.example.tmdbkotlinapp.domain.models.Actor

interface CastRepository {

    suspend fun getMovieCast(id: Int): List<Actor>
}