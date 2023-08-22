package com.example.tmdbkotlinapp.domain.repository

import androidx.paging.PagingData
import com.example.tmdbkotlinapp.data.db.entity.MovieEntity
import com.example.tmdbkotlinapp.domain.base.WorkResult
import com.example.tmdbkotlinapp.domain.models.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getRandomMovieList(page: Int, year: Int, genre: String): WorkResult<List<Movie>>

    fun getPopularMovieList():  Flow<PagingData<Movie>>

    suspend fun getMovieDetails(id: Int, remoteId: Int): WorkResult<Movie>

    suspend fun getSavedMovies(): Flow<List<MovieEntity>>

    suspend fun saveMovieInDb(movie: Movie)
}