package com.example.tmdbkotlinapp.api

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideProductApi(): MovieService {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.themoviedb.org/3/")
            .build()
        return retrofit.create()
    }

}