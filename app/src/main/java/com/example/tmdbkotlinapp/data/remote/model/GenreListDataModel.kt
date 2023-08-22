package com.example.tmdbkotlinapp.data.remote.model

import com.example.tmdbkotlinapp.domain.models.Genre

data class GenreListDataModel(
    val genres: List<GenreDataModel>
) {
    fun toDomain(): List<Genre> {
        return this.genres.map {
            it.toDomain()
        }
    }
}
