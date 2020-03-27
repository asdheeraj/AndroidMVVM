package com.dheeraj.samplemvvm.app.ui.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dheeraj.samplemvvm.data.model.MovieResponse
import com.dheeraj.samplemvvm.data.repo.movies.MovieRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent

/**
 * A View Model class to handle the business logic for Movie related activities
 *
 * @property movieRepo the MovieRepo instance
 */
class MoviesViewModel(private val movieRepo: MovieRepo): ViewModel(), KoinComponent {

    val moviesData = MutableLiveData<MovieResponse>()

    fun getMovies() {
        var movieResponse: MovieResponse? = null
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                movieResponse = movieRepo.getMovies()
            }
            moviesData.postValue(movieResponse)
        }
    }
}