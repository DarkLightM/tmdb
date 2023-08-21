package com.example.tmdbkotlinapp.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.tmdbkotlinapp.data.db.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_entity")
    fun getSavedMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie_entity WHERE id = :id")
    fun getMovieById(id: Int): MovieEntity?

    @Upsert
    fun insertMovie(movieEntity: MovieEntity)
}