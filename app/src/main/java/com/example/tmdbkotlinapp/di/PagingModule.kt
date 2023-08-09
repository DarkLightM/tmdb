package com.example.tmdbkotlinapp.di

import androidx.paging.PagingSource
import com.example.tmdbkotlinapp.data.MovieService
import com.example.tmdbkotlinapp.data.repository.PopularMoviesPagingSource
import com.example.tmdbkotlinapp.domain.models.Movie
import dagger.Module
import dagger.Provides

@Module
class PagingModule(private val movieService: MovieService) {
    @Provides
    fun providePopularMoviesPagingSource(): PagingSource<Int, Movie> {
        return PopularMoviesPagingSource(movieService)
    }
}