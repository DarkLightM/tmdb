package com.example.tmdbkotlinapp.data.db.converters

import androidx.room.TypeConverter
import com.example.tmdbkotlinapp.domain.models.Actor
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ActorConverter {
    @TypeConverter
    fun fromActorList(actorList: List<Actor>): String {
        val gson = Gson()
        return gson.toJson(actorList)
    }

    @TypeConverter
    fun toActorList(actorString: String): List<Actor> {
        return try {
            val gson = Gson()
            val listType = object : TypeToken<List<Actor>>() {}.type
            gson.fromJson(actorString, listType)
        } catch (e: Exception) {
            emptyList()
        }
    }
}