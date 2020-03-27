package com.dheeraj.samplemvvm.app.ui.movies

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.dheeraj.samplemvvm.R.layout
import com.dheeraj.samplemvvm.utils.Status.*
import kotlinx.android.synthetic.main.activity_movies.moviesProgressBar
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A View class (Activity class) to display the Movie functionality to the User
 */
class MoviesActivity : AppCompatActivity() {

    private val viewModel by viewModel<MoviesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_movies)
        initObservers()
    }

    /**
     * Helper function to initialise observers
     *
     */
    private fun initObservers() {
        viewModel.getMovies().observe(this, Observer {
            when (it.status) {
                SUCCESS -> {
                    moviesProgressBar.visibility = View.GONE
                    Log.d("Response: ", it.toString())
                }
                ERROR -> {
                    moviesProgressBar.visibility = View.GONE
                    Log.d("Error: ", it.message ?: "Unknown")
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
                LOADING -> {
                    moviesProgressBar.visibility = View.VISIBLE
                }
            }
        })
    }
}
