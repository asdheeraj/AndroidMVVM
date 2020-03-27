package com.dheeraj.samplemvvm.data.repo.movies

import com.dheeraj.samplemvvm.network.RETROFIT
import com.dheeraj.samplemvvm.network.createWebService
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 *  A koin module class to declare the repositories for Movies
 */
val moviesModule = module {
    single<MovieRepo>{MovieRepoImpl(createWebService<MoviesAPI>(get(named(RETROFIT))), MoviesEndPoints())}
}