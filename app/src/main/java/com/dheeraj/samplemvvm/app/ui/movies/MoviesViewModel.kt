package com.dheeraj.samplemvvm.app.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.dheeraj.samplemvvm.data.local.database.DatabaseService
import com.dheeraj.samplemvvm.data.repo.movies.MovieRepo
import com.dheeraj.samplemvvm.utils.Resource
import kotlinx.coroutines.Dispatchers
import org.koin.core.KoinComponent

/**
 * A View Model class to handle the business logic for Movie related activities
 *
 * @property movieRepo the MovieRepo instance
 * @property databaseService the databaseService Instance
 */
class MoviesViewModel(private val databaseService: DatabaseService, private val movieRepo: MovieRepo) : ViewModel(),
    KoinComponent {

    fun getMovies() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            databaseService.moviesDao().getAllMovies().let { localResponse ->
                if (localResponse == null) {
                    movieRepo.getMovies().let { serverResponse ->
                        databaseService.moviesDao().insertMovieResponse(serverResponse)
                        emit(Resource.success(data = serverResponse))
                    }
                } else {
                    emit(Resource.success(data = localResponse))
                }
            }
        } catch (exception: Exception) {
            emit(Resource.error(message = exception.message ?: "Unknown Error Occurred", data = null))
        }
    }
}