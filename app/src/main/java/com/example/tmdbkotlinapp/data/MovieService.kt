package com.example.tmdbkotlinapp.data

import com.example.tmdbkotlinapp.data.remote.model.ActorDataModel
import com.example.tmdbkotlinapp.data.remote.model.MovieDataModel
import com.example.tmdbkotlinapp.data.remote.model.MovieListDataModel
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query
import com.example.tmdbkotlinapp.BuildConfig

interface MovieService {

    private val TOKEN: String
        get() = BuildConfig.apiToken

    @GET("discover/movie?")
    suspend fun getRandomMovie(
        @Query("page") page: Int,
        @Query("primary_release_year") year: Int,
        @Query("with_genres") genre: String,
        @Header("Authorization") token: String = TOKEN,
    ): MovieListDataModel

    @GET("movie/popular")
    suspend fun getPopular(
        @Query("page") page: Int,
        @Header("Authorization") token: String = TOKEN,
    ): MovieListDataModel

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") id: Int,
        @Header("Authorization") token: String = TOKEN,
    ): MovieDataModel

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCast(
        @Path("movie_id") id: Int,
        @Header("Authorization") token: String = TOKEN,
    ) : ActorDataModel
}