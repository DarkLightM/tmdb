package com.example.tmdbkotlinapp.data.repository

import com.example.tmdbkotlinapp.data.MovieService
import com.example.tmdbkotlinapp.domain.models.Genre
import com.example.tmdbkotlinapp.domain.repository.GenreRepository
import javax.inject.Inject

class GenreRepositoryImpl @Inject constructor(private val movieService: MovieService) :
    GenreRepository {
    override suspend fun getGenres(): List<Genre> {
        return movieService.getGenres().toDomain()
    }
}