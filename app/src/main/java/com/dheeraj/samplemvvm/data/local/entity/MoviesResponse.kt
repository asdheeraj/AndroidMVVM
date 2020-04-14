package com.dheeraj.samplemvvm.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Model class for Movie Response
 *
 */
@Entity(tableName = "moviesResponse")
data class MovieResponse(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "remoteId")
    val id: Long = 0,
    @ColumnInfo(name = "page")
    val page: Int? = null,
    @ColumnInfo(name = "movies")
    val results: List<Movie>? = null,
    @ColumnInfo(name = "total_pages")
    val totalPages: Int? = null,
    @ColumnInfo(name = "total_results")
    val totalResults: Int? = null
)

@Entity(tableName = "movie")
data class Movie(
    @ColumnInfo(name = "adult")
    val adult: Boolean? = null,
    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String? = null,
    @ColumnInfo(name = "genre_ids")
    val genreIds: List<Int>? = null,
    @PrimaryKey @ColumnInfo(name = "localId")
    val id: Int? = null,
    @ColumnInfo(name = "original_language")
    val originalLanguage: String? = null,
    @ColumnInfo(name = "original_title")
    val originalTitle: String? = null,
    @ColumnInfo(name = "overview")
    val overview: String? = null,
    @ColumnInfo(name = "popularity")
    val popularity: Double? = null,
    @ColumnInfo(name = "poster_path")
    val posterPath: String? = null,
    @ColumnInfo(name = "release_date")
    val releaseDate: String? = null,
    @ColumnInfo(name = "title")
    val title: String? = null,
    @ColumnInfo(name = "video")
    val video: Boolean? = null,
    @ColumnInfo(name = "vote_average")
    val voteAverage: Float? = null,
    @ColumnInfo(name = "vote_count")
    val voteCount: Int? = null
)