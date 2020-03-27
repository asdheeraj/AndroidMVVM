package com.dheeraj.samplemvvm.app.ui.movies

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.dheeraj.samplemvvm.R.layout
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A View class (Activity class) to display the Movie functionality to the User
 */
class MoviesActivity : AppCompatActivity() {

    private val viewModel by viewModel<MoviesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_movies)
        getMovies()
        initObservers()
    }

    /**
     * Helper function to get the Movie details
     *
     */
    private fun getMovies() {
        viewModel.getMovies()
    }

    /**
     * Helper function to initialise observers
     *
     */
    private fun initObservers() {
        viewModel.moviesData.observe(this, Observer {
            Log.d("Response: ", it.toString())
        })
    }
}
