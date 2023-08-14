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
            this.id,
            this.title,
            this.genre,
            this.overview,
            this.releaseDate,
            this.rating,
            0F,
            this.posterPath,
            this.actors
        )
    }
}
