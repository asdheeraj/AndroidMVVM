package com.dheeraj.samplemvvm.app.ui

import com.dheeraj.samplemvvm.app.ui.movies.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * A Module class for Koin describing the view models
 */
val movieVMModule = module {
  viewModel { MoviesViewModel(get()) }
}