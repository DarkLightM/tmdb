package com.example.tmdbkotlinapp.data.db.converters

import androidx.room.TypeConverter
import com.example.tmdbkotlinapp.domain.models.Genre

class GenreConverter {

    @TypeConverter
    fun fromGenreList(genreList: List<Genre>): String {
        return genreList.joinToString(",") { "${it.id};${it.name}" }
    }

    @TypeConverter
    fun toGenreList(genreString: String): List<Genre> {
        val genreEntries = genreString.split(",")
        return genreEntries.map {
            val parts = it.split(";")
            Genre(parts[0].toInt(), parts[1])
        }
    }
}