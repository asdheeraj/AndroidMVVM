package com.dheeraj.samplemvvm.data.repo.movies

import com.dheeraj.samplemvvm.data.model.MovieResponse

class MovieRepoImpl(private val moviesAPI: MoviesAPI,
    private val moviesEndPoints: MoviesEndPoints): MovieRepo {

    companion object {
        const val API_KEY = "f559cbf805979a54e983cb35c5cfbc26"
    }

    override suspend fun getMovies(): MovieResponse {
        return moviesAPI.getMovies(moviesEndPoints.getMovies(), API_KEY)
    }
}