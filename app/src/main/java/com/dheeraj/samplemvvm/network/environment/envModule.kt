package com.dheeraj.samplemvvm.network.environment

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Definition of the envModule for Dependency Injection using Koin
 */

val envModule = module {
    factory {
        EnvironmentManager(
            androidContext().getSharedPreferences(
                EnvironmentManager.PREF_NAME,
                Context.MODE_PRIVATE
            )
        )
    }
}