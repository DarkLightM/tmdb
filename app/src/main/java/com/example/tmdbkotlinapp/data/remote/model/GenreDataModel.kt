package com.example.tmdbkotlinapp.data.remote.model

import com.example.tmdbkotlinapp.domain.models.Genre

data class GenreDataModel(
    val id: Int,
    val name: String,
) {
    fun toDomain(): Genre {
        return Genre(id = id, name = name)
    }
}
