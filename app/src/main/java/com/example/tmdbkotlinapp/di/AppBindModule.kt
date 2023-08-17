package com.example.tmdbkotlinapp.di

import com.example.tmdbkotlinapp.data.repository.CastRepositoryImpl
import com.example.tmdbkotlinapp.data.repository.GenreRepositoryImpl
import com.example.tmdbkotlinapp.data.repository.MovieRepositoryImpl
import com.example.tmdbkotlinapp.domain.repository.CastRepository
import com.example.tmdbkotlinapp.domain.repository.GenreRepository
import com.example.tmdbkotlinapp.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module

@Module
interface AppBindModule {

    @Binds
    fun bindMovieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository

    @Binds
    fun bindCastRepository(castRepositoryImpl: CastRepositoryImpl): CastRepository

    @Binds
    fun bindGenreRepository(genreRepositoryImpl: GenreRepositoryImpl): GenreRepository
}