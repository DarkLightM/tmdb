package com.example.tmdbkotlinapp.di

import com.example.tmdbkotlinapp.data.repository.CastRepositoryImpl
import com.example.tmdbkotlinapp.domain.repository.CastRepository
import com.example.tmdbkotlinapp.domain.repository.MovieRepository
import com.example.tmdbkotlinapp.data.repository.MovieRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface AppBindModule {

    @Binds
    fun bindMovieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository

    @Binds
    fun bindCastRepository(castRepositoryImpl: CastRepositoryImpl): CastRepository
}