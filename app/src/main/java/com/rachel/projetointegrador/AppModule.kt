package com.rachel.projetointegrador

import com.rachel.projetointegrador.data.repository.CastRemoteRepository
import com.rachel.projetointegrador.data.repository.CastRepository
import com.rachel.projetointegrador.data.repository.GenreRemoteRepository
import com.rachel.projetointegrador.data.repository.GenreRepository
import com.rachel.projetointegrador.presentation.CastViewModel
import com.rachel.projetointegrador.presentation.MoviesViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<CastRepository> { CastRemoteRepository() }

    single<GenreRepository> { GenreRemoteRepository() }

    viewModel { CastViewModel(get()) }

    viewModel { MoviesViewModel(application = androidApplication(), get())}
}