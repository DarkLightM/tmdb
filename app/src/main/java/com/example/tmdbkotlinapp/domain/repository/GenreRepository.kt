package com.example.tmdbkotlinapp.domain.repository

import com.example.tmdbkotlinapp.domain.base.WorkResult
import com.example.tmdbkotlinapp.domain.models.Genre

interface GenreRepository {
    suspend fun getGenres(): WorkResult<List<Genre>>
}