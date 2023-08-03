package com.example.tmdbkotlinapp.di

import com.example.tmdbkotlinapp.api.repository.cast.CastRepositoryImpl
import com.example.tmdbkotlinapp.api.repository.cast.CastRepository
import com.example.tmdbkotlinapp.api.repository.movie.MovieRepository
import com.example.tmdbkotlinapp.api.repository.movie.MovieRepositoryImpl
import com.example.tmdbkotlinapp.api.usecase.movie.GetRandomMovieUseCase
import dagger.Binds
import dagger.Module

@Module
interface AppBindModule {

    @Binds
    fun bindMovieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository

    @Binds
    fun bindCastRepository(castRepositoryImpl: CastRepositoryImpl): CastRepository
}