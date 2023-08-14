package com.example.tmdbkotlinapp.data.db.converters

import androidx.room.TypeConverter
import com.example.tmdbkotlinapp.domain.models.Actor

class ActorConverter {
    @TypeConverter
    fun fromActorList(actorList: List<Actor>): String {
        return actorList.joinToString(",") { "${it.actorId};${it.name};${it.image}" }
    }

    @TypeConverter
    fun toActorList(actorString: String): List<Actor> {
        val actorEntries = actorString.split(",")
        return actorEntries.map {
            val parts = it.split(";")
            Actor(parts[0].toInt(), parts[1], parts[2])
        }
    }
}