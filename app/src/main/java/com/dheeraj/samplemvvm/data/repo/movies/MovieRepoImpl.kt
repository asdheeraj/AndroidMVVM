package com.dheeraj.samplemvvm.data.repo.movies

import com.dheeraj.samplemvvm.BuildConfig
import com.dheeraj.samplemvvm.data.local.entity.MovieResponse

class MovieRepoImpl(
    private val moviesAPI: MoviesAPI,
    private val moviesEndPoints: MoviesEndPoints
) : MovieRepo {

    override suspend fun getMovies(): MovieResponse =
        moviesAPI.getMovies(moviesEndPoints.getMovies(), BuildConfig.API_KEY)
}