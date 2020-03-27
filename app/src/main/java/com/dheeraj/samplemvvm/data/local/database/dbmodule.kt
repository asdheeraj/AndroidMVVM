package com.dheeraj.samplemvvm.data.local.database

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single { Room.databaseBuilder(androidContext().applicationContext, DatabaseService::class.java, "moviedb").build() }
}