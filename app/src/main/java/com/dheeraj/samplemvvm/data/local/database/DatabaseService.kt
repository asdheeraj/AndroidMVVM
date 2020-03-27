package com.dheeraj.samplemvvm.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dheeraj.samplemvvm.data.local.dao.MoviesDao
import com.dheeraj.samplemvvm.data.local.entity.Movie
import com.dheeraj.samplemvvm.data.local.entity.MovieResponse

@Database(entities = [MovieResponse::class, Movie::class], version = 1, exportSchema = false)
@TypeConverters(DataConverter::class)
abstract class DatabaseService: RoomDatabase() {
    abstract fun moviesDao() : MoviesDao
}