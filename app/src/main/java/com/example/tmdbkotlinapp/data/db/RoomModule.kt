package com.example.tmdbkotlinapp.data.db

import android.content.Context
import androidx.room.Room
import com.example.tmdbkotlinapp.data.db.dao.MovieDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Provides
    @Singleton
    fun provideAppDatabase(application: Context): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "app_database")
            .build()
    }

    @Provides
    fun provideMovieDao(appDatabase: AppDatabase): MovieDao {
        return appDatabase.getMovieDao()
    }
}