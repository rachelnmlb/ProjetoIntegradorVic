package com.rachel.projetointegrador


import com.rachel.projetointegrador.data.database.FavoriteMovieDatabase
import com.rachel.projetointegrador.data.repository.*
import com.rachel.projetointegrador.presentation.CastViewModel
import com.rachel.projetointegrador.presentation.MovieDetailViewModel
import com.rachel.projetointegrador.presentation.MoviesViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<CastRepository> { CastRemoteRepository() }

    single<GenreRepository> { GenreRemoteRepository() }

    single { FavoriteMovieDatabase.getDatabase(get()).favoriteMovieDao() }

    single<FavoriteMovieRepository> { FavoriteMovieLocalRepository(get()) }

    viewModel { CastViewModel(get()) }

    viewModel { MoviesViewModel(get(), get())}

    viewModel { MovieDetailViewModel(get()) }
}