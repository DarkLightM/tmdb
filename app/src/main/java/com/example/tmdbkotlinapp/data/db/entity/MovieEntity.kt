package com.example.tmdbkotlinapp.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tmdbkotlinapp.domain.models.Actor
import com.example.tmdbkotlinapp.domain.models.Genre
import com.example.tmdbkotlinapp.domain.models.Movie

@Entity("movie_entity")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val remoteId: Int,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val rating: Float,
    val posterPath: String,
    val genre: List<Genre>,
    val actors: List<Actor>,
) {
    fun toDomain(): Movie {
        return Movie(
            movieId = this.id,
            movieRemoteId = this.remoteId,
            originalTitle = this.title,
            genreList = this.genre,
            overview = this.overview,
            releaseDate = this.releaseDate,
            rating = this.rating,
            popularity = 0F,
            posterPath = this.posterPath,
            cast = this.actors
        )
    }
}
