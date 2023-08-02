package com.example.tmdbkotlinapp.api

import com.example.tmdbkotlinapp.models.actor.ActorDataModel
import com.example.tmdbkotlinapp.models.movie.Movie
import com.example.tmdbkotlinapp.models.movie.MovieListDataModel
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    companion object{
        const val BEARER_TOKEN="eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4YmViZjAyMDY1ZDEyMGJmMGQyMmUyOWFhNzM3NjIxZCIsInN1YiI6IjY0Yzc5NjQzZWVjNWI1NThlYThiNjIyOCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.WpSTBvcWIoXd8OktGiijRoFlSxSPHqTfFDGAO3Y2Fhs"
    }

    @GET("discover/movie?")
    suspend fun getRandomMovie(
        @Query("page") page: Int,
        @Query("primary_release_year") year: Int,
        @Query("with_genres") genre: String,
        @Header("Authorization") token: String,
    ): MovieListDataModel

    @GET("movie/popular")
    suspend fun getPopular(
        @Header("Authorization") token: String,
    ): MovieListDataModel

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") id: Int,
        @Header("Authorization") token: String,
    ): Movie

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCast(
        @Path("movie_id") id: Int,
        @Header("Authorization") token: String,
    ) : ActorDataModel
}