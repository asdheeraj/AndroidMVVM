package com.dheeraj.samplemvvm.data.local.database

import androidx.room.TypeConverter
import com.dheeraj.samplemvvm.data.local.entity.Movie
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class DataConverter  {
    @TypeConverter // note this annotation
    fun fromMoviesList(optionValues: List<Movie>?): String? {
        if (optionValues == null) {
            return null
        }
        val gson = Gson()
        val type: Type =
            object : TypeToken<List<Movie?>?>() {}.type
        return gson.toJson(optionValues, type)
    }

    @TypeConverter // note this annotation
    fun toMoviesList(moviesString: String?): List<Movie>? {
        if (moviesString == null) {
            return null
        }
        val gson = Gson()
        val type: Type =
            object : TypeToken<List<Movie?>?>() {}.type
        return gson.fromJson(moviesString, type)
    }

    @TypeConverter // note this annotation
    fun fromIntList(optionValues: List<Int>?): String? {
        if (optionValues == null) {
            return null
        }
        val gson = Gson()
        val type: Type =
            object : TypeToken<List<Int?>?>() {}.type
        return gson.toJson(optionValues, type)
    }

    @TypeConverter // note this annotation
    fun toIntList(string: String?): List<Int>? {
        if (string == null) {
            return null
        }
        val gson = Gson()
        val type: Type =
            object : TypeToken<List<Int?>?>() {}.type
        return gson.fromJson(string, type)
    }
}