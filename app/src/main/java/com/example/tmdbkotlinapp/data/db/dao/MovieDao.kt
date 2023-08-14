package com.example.tmdbkotlinapp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.tmdbkotlinapp.data.db.entity.MovieEntity
import androidx.room.Query

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_entity")
    fun getSavedMovies(): List<MovieEntity>

    @Query("SELECT * FROM movie_entity WHERE id = :id")
    fun getMovieById(id: Int): MovieEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movieEntity: MovieEntity)
}