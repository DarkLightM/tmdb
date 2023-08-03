package com.example.tmdbkotlinapp.api

import com.example.tmdbkotlinapp.models.actor.ActorDataModel
import com.example.tmdbkotlinapp.models.movie.Movie
import com.example.tmdbkotlinapp.models.movie.MovieListDataModel
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
        @Header("Authorization") token: String = TOKEN,
    ): MovieListDataModel

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") id: Int,
        @Header("Authorization") token: String = TOKEN,
    ): Movie

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCast(
        @Path("movie_id") id: Int,
        @Header("Authorization") token: String = TOKEN,
    ) : ActorDataModel
}