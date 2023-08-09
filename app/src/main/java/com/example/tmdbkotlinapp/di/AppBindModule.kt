package com.example.tmdbkotlinapp.di

import androidx.paging.PagingSource
import com.example.tmdbkotlinapp.data.repository.CastRepositoryImpl
import com.example.tmdbkotlinapp.domain.repository.CastRepository
import com.example.tmdbkotlinapp.domain.repository.MovieRepository
import com.example.tmdbkotlinapp.data.repository.MovieRepositoryImpl
import com.example.tmdbkotlinapp.data.repository.PopularMoviesPagingSource
import com.example.tmdbkotlinapp.domain.models.Movie
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface AppBindModule {

    @Binds
    fun bindMovieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository

    @Binds
    fun bindCastRepository(castRepositoryImpl: CastRepositoryImpl): CastRepository
}