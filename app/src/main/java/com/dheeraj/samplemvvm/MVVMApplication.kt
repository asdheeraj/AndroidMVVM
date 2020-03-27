package com.dheeraj.samplemvvm

import android.app.Application
import com.dheeraj.samplemvvm.app.ui.movieVMModule
import com.dheeraj.samplemvvm.data.local.database.databaseModule
import com.dheeraj.samplemvvm.data.repo.movies.moviesModule
import com.dheeraj.samplemvvm.network.environment.envModule
import com.dheeraj.samplemvvm.network.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MVVMApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MVVMApplication)
            modules(
                listOf(
                    networkModule, envModule, moviesModule, movieVMModule, databaseModule
                )
            )
        }
    }
}