package com.dheeraj.samplemvvm.network.environment

import android.content.SharedPreferences
import com.dheeraj.samplemvvm.BuildConfig
import com.dheeraj.samplemvvm.extensions.put

class EnvironmentManager (private val sharedPreferences: SharedPreferences) {
    companion object {
        const val PREF_NAME = "pref_env"
        private const val PREF_KEY_ENV = "env"
    }

    private val environment: Environment

    init {
        var env = sharedPreferences.getString(PREF_KEY_ENV, "")

        if (env.isNullOrEmpty()) {
            env = BuildConfig.BUILD_TYPE
        }

        environment = when (env) {
            Environment.Type.DEBUG.name -> DebugEnv()
            Environment.Type.QA.name -> QaEnv()
            Environment.Type.RELEASE.name -> ReleaseEnv()
            else -> DebugEnv()
        }
    }

    /**
     * Returns the baseUrl
     */
    fun baseUrl() = environment.baseUrl()

    /**
     * Returns the type of Environment
     */
    fun type() = environment.type()

    /**
     * Changes the Environment, but need to restart app to take effect after changing the
     * environment.
     */
    fun setEnvironemnt(environment: Environment) {
        sharedPreferences.put(PREF_KEY_ENV, environment.type().name)
    }
}