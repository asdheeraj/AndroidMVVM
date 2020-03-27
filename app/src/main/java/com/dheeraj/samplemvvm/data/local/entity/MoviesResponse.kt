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
    val total_pages: Int? = null,
    @ColumnInfo(name = "total_results")
    val total_results: Int? = null
)

@Entity(tableName = "movie")
data class Movie(
    @ColumnInfo(name = "adult")
    val adult: Boolean? = null,
    @ColumnInfo(name = "backdrop_path")
    val backdrop_path: String? = null,
    @ColumnInfo(name = "genre_ids")
    val genre_ids: List<Int>? = null,
    @PrimaryKey @ColumnInfo(name = "localId")
    val id: Int? = null,
    @ColumnInfo(name = "original_language")
    val original_language: String? = null,
    @ColumnInfo(name = "original_title")
    val original_title: String? = null,
    @ColumnInfo(name = "overview")
    val overview: String? = null,
    @ColumnInfo(name = "popularity")
    val popularity: Double? = null,
    @ColumnInfo(name = "poster_path")
    val poster_path: String? = null,
    @ColumnInfo(name = "release_date")
    val release_date: String? = null,
    @ColumnInfo(name = "title")
    val title: String? = null,
    @ColumnInfo(name = "video")
    val video: Boolean? = null,
    @ColumnInfo(name = "vote_average")
    val vote_average: Float? = null,
    @ColumnInfo(name = "vote_count")
    val vote_count: Int? = null
)