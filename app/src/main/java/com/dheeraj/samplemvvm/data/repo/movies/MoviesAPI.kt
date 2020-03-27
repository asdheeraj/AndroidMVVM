package com.dheeraj.samplemvvm.data.repo.movies

import com.dheeraj.samplemvvm.data.local.entity.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * Retrofit instance class to get the Movie details
 */
interface MoviesAPI  {

    /**
     * Gets the movies from the server
     *
     * @param url the url to get movies
     * @param apiKey the api key value
     * @return Movie Response
     */

    @GET
    suspend fun getMovies(
        @Url url: String,
        @Query("api_key") apiKey: String
    ) : MovieResponse
}