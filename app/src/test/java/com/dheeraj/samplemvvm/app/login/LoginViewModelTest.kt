package com.dheeraj.samplemvvm.app.login

import com.dheeraj.samplemvvm.app.ui.login.LoginViewModel
import org.junit.Assert.*

import org.junit.*
import org.mockito.*

class LoginViewModelTest {

    private val loginViewModel: LoginViewModel = Mockito.mock(LoginViewModel::class.java)

    /**
     * Test case for checkCredentials - valid Username, valid password
     */
    @Test
    fun checkCredentialsSuccessValidUsernameValidPassword() {
        val loginResult = loginViewModel.checkCredentials("admin", "admin")
        assertFalse(loginResult)
    }

    /**
     * Test case for checkCredentials - valid Username, Invalid password
     */
    @Test
    fun checkCredentialsFailureValidUsernameInvalidPassword() {
        val loginResult = loginViewModel.checkCredentials("admin", "wrong")
        assertFalse(loginResult)
    }

    /**
     * Test case for checkCredentials - Invalid Username, valid password
     */
    @Test
    fun checkCredentialsFailureInvalidUsernameValidPassword() {
        val loginResult = loginViewModel.checkCredentials("NOT", "PASSWORD")
        assertFalse(loginResult)
    }

    /**
     * Test case for checkCredentials - Invalid Username, Invalid password
     */
    @Test
    fun checkCredentialsFailureInvalidUsernameInvalidPassword() {
        val loginResult = loginViewModel.checkCredentials("NOT", "WRONG")
        assertFalse(loginResult)
    }
}