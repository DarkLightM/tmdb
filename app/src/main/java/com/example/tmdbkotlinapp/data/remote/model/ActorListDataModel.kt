package com.example.tmdbkotlinapp.data.remote.model

import com.example.tmdbkotlinapp.data.repository.DEFAULT_IMG_URL
import com.example.tmdbkotlinapp.domain.models.Actor
import com.google.gson.annotations.SerializedName

data class ActorListDataModel(
    @SerializedName("id") val movieId: Int,
    @SerializedName("cast") val cast: List<ActorDataModel>,
) {
    fun toDomain(): List<Actor> {
        return this.cast.map {
            Actor(
                actorId = it.actorId,
                name = it.name,
                image = DEFAULT_IMG_URL.format(it.image),
            )
        }
    }
}
