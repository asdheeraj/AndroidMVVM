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
    fun checkCredentials_success_validUsername_validPassword() {
        val username = "admin"
        val password = "admin"
        val loginResult = loginViewModel.checkCredentials(username, password)
        assertFalse(loginResult)
    }

    /**
     * Test case for checkCredentials - valid Username, Invalid password
     */
    @Test
    fun checkCredentials_failure_validUsername_invalidPassword() {
        val username = "admin"
        val password = "adminasd"
        val loginResult = loginViewModel.checkCredentials(username, password)
        assertFalse(loginResult)
    }

    /**
     * Test case for checkCredentials - Invalid Username, valid password
     */
    @Test
    fun checkCredentials_failure_invalidUsername_validPassword() {
        val username = "adminfasd"
        val password = "admin"
        val loginResult = loginViewModel.checkCredentials(username, password)
        assertFalse(loginResult)
    }

    /**
     * Test case for checkCredentials - Invalid Username, Invalid password
     */
    @Test
    fun checkCredentials_failure_invalidUsername_invalidPassword() {
        val username = "adminfasd"
        val password = "adminasd"
        val loginResult = loginViewModel.checkCredentials(username, password)
        assertFalse(loginResult)
    }
}