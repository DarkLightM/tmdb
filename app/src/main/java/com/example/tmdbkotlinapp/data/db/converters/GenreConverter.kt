package com.example.tmdbkotlinapp.data.db.converters

import androidx.room.TypeConverter
import com.example.tmdbkotlinapp.domain.models.Genre
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreConverter {

    @TypeConverter
    fun fromGenreList(genreList: List<Genre>): String {
        val gson = Gson()
        return gson.toJson(genreList)
    }

    @TypeConverter
    fun toGenreList(genreString: String): List<Genre> {
        val gson = Gson()
        val listType = object : TypeToken<List<Genre>>() {}.type
        return gson.fromJson(genreString, listType)
    }
}