package com.example.tmdbkotlinapp.api.repository.cast

import com.example.tmdbkotlinapp.models.actor.Actor

interface ICastRepository {

    suspend fun getMovieCast(id: Int): List<Actor>
}