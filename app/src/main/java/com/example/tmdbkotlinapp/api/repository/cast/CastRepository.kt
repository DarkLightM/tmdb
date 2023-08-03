package com.example.tmdbkotlinapp.api.repository.cast

import com.example.tmdbkotlinapp.models.actor.Actor

interface CastRepository {

    suspend fun getMovieCast(id: Int): List<Actor>
}