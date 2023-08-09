package com.example.tmdbkotlinapp.data.remote.model

import com.example.tmdbkotlinapp.domain.models.Actor
import com.google.gson.annotations.SerializedName

data class ActorDataModel(
    @SerializedName("id")
    val actorId: Int,
    @SerializedName("original_name")
    val name: String,
    @SerializedName("profile_path")
    val image: String,
) {
    fun toDomain(): Actor {
        return Actor(
            actorId = this.actorId,
            name = this.name,
            image = this.image
        )
    }
}
