package com.example.tmdbkotlinapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.tmdbkotlinapp.data.db.converters.ActorConverter
import com.example.tmdbkotlinapp.data.db.converters.GenreConverter
import com.example.tmdbkotlinapp.data.db.dao.MovieDao
import com.example.tmdbkotlinapp.data.db.entity.MovieEntity

@Database(
    version = 1,
    entities = [MovieEntity::class])
@TypeConverters(GenreConverter::class, ActorConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getMovieDao(): MovieDao
}