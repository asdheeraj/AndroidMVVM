package com.dheeraj.samplemvvm.data.repo.movies

import com.dheeraj.samplemvvm.data.local.entity.MovieResponse

/**
 * Repository to handle Movie related calls
 *
 */

interface MovieRepo {

    /**
     * API call to get Movies
     *
     * @return Movie response
     */
    suspend fun getMovies() : MovieResponse
}