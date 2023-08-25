package com.example.tmdbkotlinapp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.tmdbkotlinapp.data.db.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_entity")
    fun getSavedMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie_entity WHERE remoteId = :remoteId")
    fun getMovieByRemoteId(remoteId: Int): MovieEntity?

    @Insert
    fun insertMovie(movieEntity: MovieEntity)

    @Query("DELETE FROM movie_entity WHERE remoteId = :remoteId")
    fun deleteMovie(remoteId: Int)

    @Query("SELECT COUNT(*) FROM movie_entity WHERE remoteId = :remoteId")
    fun isMovieInDb(remoteId: Int): Flow<Int>
}