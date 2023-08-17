package com.example.tmdbkotlinapp.data.remote.model

import com.example.tmdbkotlinapp.domain.models.Genre

data class GenreListDataModel(
    val genres: List<Genre>
) {
    fun toDomain(): List<Genre> {
        return this.genres
    }
}
