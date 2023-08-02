package com.example.tmdbkotlinapp.di

import com.example.tmdbkotlinapp.api.repository.cast.CastRepository
import com.example.tmdbkotlinapp.api.repository.cast.ICastRepository
import com.example.tmdbkotlinapp.api.repository.movie.IMovieRepository
import com.example.tmdbkotlinapp.api.repository.movie.MovieRepository
import com.example.tmdbkotlinapp.api.usecase.movie.GetRandomMovieUseCase
import com.example.tmdbkotlinapp.api.usecase.movie.IGetRandomMovieUseCase
import dagger.Binds
import dagger.Module

@Module
interface AppBindModule {

    @Binds
    fun bindMovieRepository(movieRepository: MovieRepository): IMovieRepository

    @Binds
    fun bindCastRepository(castRepository: CastRepository): ICastRepository

    @Binds
    fun bindRandomMovieUseCase(getRandomMovieUseCase: GetRandomMovieUseCase): IGetRandomMovieUseCase
}