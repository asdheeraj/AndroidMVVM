package com.dheeraj.samplemvvm.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.dheeraj.samplemvvm.data.local.entity.Movie
import com.dheeraj.samplemvvm.data.local.entity.MovieResponse

@Dao
interface MoviesDao {
    /**
     * Inserts [movie] into Db and returns the id
     */
    @Insert
    suspend fun insertMovie(movie: Movie): Long

    /**
     * Updates the [movie] and returns the number of rows updated
     */
    @Update
    suspend fun updateMovie(movie: Movie): Int

    /**
     * Deletes the [movie] and returns the number of rows deleted
     */
    @Delete
    suspend fun deleteMovie(movie: Movie): Int

    /**
     * Inserts [movieResponse] into Db and returns the id of the Response
     */
    @Insert
    suspend fun insertMovieResponse(movieResponse: MovieResponse): Long

    /**
     * Returns all the movies in local database
     */
    @Query("SELECT * FROM moviesResponse")
    suspend fun getAllMovies(): MovieResponse?

    /**
     * Returns the movie from local database with respective [id]
     */
    @Query("SELECT * FROM movie WHERE localId = :id LIMIT 1")
    suspend fun getMovieById(id: Int): Movie

    /**
     * Returns the count of movies in the database
     */
    @Query("SELECT count(movies) FROM moviesResponse")
    suspend fun count(): Int
}