package com.example.tmdbkotlinapp.domain.repository

import com.example.tmdbkotlinapp.domain.base.WorkResult
import com.example.tmdbkotlinapp.domain.models.Actor

interface CastRepository {

    suspend fun getMovieCast(id: Int): WorkResult<List<Actor>>
}