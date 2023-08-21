package com.example.tmdbkotlinapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tmdbkotlinapp.ui.genres_bottom_sheet.GenreBottomSheetViewModel
import com.example.tmdbkotlinapp.ui.movie_details.MovieDetailsViewModel
import com.example.tmdbkotlinapp.ui.popular_movies.PopularMoviesViewModel
import com.example.tmdbkotlinapp.ui.random_movie.RandomMovieViewModel
import com.example.tmdbkotlinapp.ui.saved_movies.SavedMoviesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(RandomMovieViewModel::class)
    abstract fun bindRandomMovieViewModel(randomMovieViewModel: RandomMovieViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PopularMoviesViewModel::class)
    abstract fun bindPopularMovieViewModel(popularMoviesViewModel: PopularMoviesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailsViewModel::class)
    abstract fun bindMovieDetailsViewModel(movieDetailsViewModel: MovieDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SavedMoviesViewModel::class)
    abstract fun bindSavedMoviesViewModel(savedMoviesViewModel: SavedMoviesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(GenreBottomSheetViewModel::class)
    abstract fun bindGenreBottomSheetViewModel(genreBottomSheetViewModel: GenreBottomSheetViewModel): ViewModel
}