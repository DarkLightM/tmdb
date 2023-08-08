package com.example.tmdbkotlinapp.domain.models

import com.google.gson.annotations.SerializedName

data class Actor(
    val actorId: Int,
    val name: String,
    val image: String?,
)
