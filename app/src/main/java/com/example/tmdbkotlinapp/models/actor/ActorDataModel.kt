package com.example.tmdbkotlinapp.models.actor

import com.google.gson.annotations.SerializedName

data class ActorDataModel(
    @SerializedName("id")
    val movieId: Int,
    val cast: List<Actor>,
) {
    fun toDomain(): List<Actor> {
        return this.cast.map {
            Actor(
                actorId = it.actorId,
                name = it.name,
                image = it.image,
            )
        }
    }
}
