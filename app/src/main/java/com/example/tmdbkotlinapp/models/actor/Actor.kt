package com.example.tmdbkotlinapp.models.actor

import com.google.gson.annotations.SerializedName

data class Actor(
    @SerializedName("id")
    val actorId: Int,
    val name: String,
    @SerializedName("profile_path")
    val image: String?,
)
