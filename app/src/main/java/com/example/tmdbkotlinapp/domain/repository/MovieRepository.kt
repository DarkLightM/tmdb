package com.example.tmdbkotlinapp.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.tmdbkotlinapp.data.remote.model.MovieDataModel
import com.example.tmdbkotlinapp.domain.models.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getRandomMovieList(page: Int, year: Int, genre: String): List<Movie>

    fun getPopularMovieList(): LiveData<PagingData<Movie>>

    suspend fun getMovieDetails(id: Int): Movie

    suspend fun getTotalPages(year: Int, genre: String): Int
}