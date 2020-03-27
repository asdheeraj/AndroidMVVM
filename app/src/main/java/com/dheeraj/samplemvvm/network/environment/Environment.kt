package com.dheeraj.samplemvvm.network.environment

import com.dheeraj.samplemvvm.BuildConfig
import com.dheeraj.samplemvvm.network.environment.Environment.Type
import com.dheeraj.samplemvvm.network.environment.Environment.Type.DEBUG
import com.dheeraj.samplemvvm.network.environment.Environment.Type.QA
import com.dheeraj.samplemvvm.network.environment.Environment.Type.RELEASE

interface Environment {
    enum class Type constructor(val value: String) {
        DEBUG("debug"), QA("qa"), RELEASE("release")
    }

    fun baseUrl(): String

    fun type(): Type
}

/**
 * This class extends the [Environment] interface and defines the values for base URL
 */
class DebugEnv : Environment {
    override fun baseUrl(): String = BuildConfig.BASE_URL

    override fun type(): Type =
        DEBUG
}

/**
 * This class extends the [Environment] interface and defines the values for baseURL
 */
class QaEnv : Environment {
    override fun baseUrl(): String = BuildConfig.BASE_URL

    override fun type(): Type =
        QA
}

/**
 * This class extends the [Environment] interface and defines the values for baseURL
 */
class ReleaseEnv : Environment {
    override fun baseUrl(): String = BuildConfig.BASE_URL

    override fun type(): Type =
        RELEASE
}