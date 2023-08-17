package com.example.tmdbkotlinapp.di

import com.example.tmdbkotlinapp.data.NetworkModule
import com.example.tmdbkotlinapp.data.db.RoomModule
import com.example.tmdbkotlinapp.ui.genres_bottom_sheet.GenreBottomSheet
import com.example.tmdbkotlinapp.ui.movie_details.MovieDetailsFragment
import com.example.tmdbkotlinapp.ui.popular_movies.PopularMoviesFragment
import com.example.tmdbkotlinapp.ui.random_movie.RandomMovieFragment
import com.example.tmdbkotlinapp.ui.saved_movies.SavedMoviesFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppBindModule::class,
    NetworkModule::class,
    ViewModelModule::class,
    PagingModule::class,
    RoomModule::class,
    ContextModule::class])
interface AppComponent {
    fun viewModelFactory(): ViewModelFactory
    fun inject(fragment: RandomMovieFragment)
    fun inject(fragment: MovieDetailsFragment)
    fun inject(fragment: PopularMoviesFragment)
    fun inject(fragment: SavedMoviesFragment)

    fun inject(fragment: GenreBottomSheet)
}