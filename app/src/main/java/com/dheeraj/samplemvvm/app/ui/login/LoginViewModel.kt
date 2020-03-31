package com.dheeraj.samplemvvm.app.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * A view model class where all the Business logic wrt the login functionality is handled
 */
open class LoginViewModel: ViewModel() {

    val loginResult = MutableLiveData<Boolean>()

    /**
     * Performs Login with the given username and password
     *
     * @param username The username value
     * @param password The password value
     */
    fun performLogin(username: String, password: String) = loginResult.postValue(checkCredentials(username, password))

    /**
     * Checks the correctness of the credentials provided
     *
     * @param username the user name value
     * @param password the password value
     * @return boolean value of the credentials correctness
     */
    fun checkCredentials(username: String, password: String): Boolean  = username == "admin" && password == "admin"

}