package com.example.tmdbkotlinapp.domain.repository

import androidx.paging.PagingData
import com.example.tmdbkotlinapp.data.db.entity.MovieEntity
import com.example.tmdbkotlinapp.data.repository.DataSource
import com.example.tmdbkotlinapp.domain.models.Actor
import com.example.tmdbkotlinapp.domain.models.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getRandomMovieList(page: Int, year: Int, genre: String): List<Movie>

    fun getPopularMovieList(): Flow<PagingData<Movie>>

    suspend fun getMovieDetails(id: Int, source: DataSource): Movie

    suspend fun getTotalPages(year: Int, genre: String): Int

    suspend fun getSavedMovies(): Flow<List<MovieEntity>>

    suspend fun saveMovieInDb(movie: Movie, actors: List<Actor>)
}