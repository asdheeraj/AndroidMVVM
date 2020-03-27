package com.dheeraj.samplemvvm.app.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dheeraj.samplemvvm.R.layout
import com.dheeraj.samplemvvm.R.string
import com.dheeraj.samplemvvm.app.ui.login.LoginViewModel
import com.dheeraj.samplemvvm.app.ui.movies.MoviesActivity
import kotlinx.android.synthetic.main.activity_login.button_login
import kotlinx.android.synthetic.main.activity_login.login_edit_text
import kotlinx.android.synthetic.main.activity_login.passord_edit_text

/**
 * A View class (Activity class) to display the Login functionality to the User
 */
class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_login)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        initListeners()
        initObservers()
    }

    /**
     * Initialises the Observers
     */
    private fun initObservers() {
        viewModel.loginResult.observe(this, Observer { result ->
            result?.let {
                if (it)
                    startActivity(Intent(this, MoviesActivity::class.java))
                else
                    Toast.makeText(this, getString(string.login_error_message), Toast.LENGTH_LONG).show()
            }
        })
    }

    /**
     * Initialises the Listeners
     */
    private fun initListeners() {
        button_login.setOnClickListener {
            viewModel.performLogin(
                username = login_edit_text.text.toString(),
                password = passord_edit_text.text.toString()
            )
        }
    }
}
