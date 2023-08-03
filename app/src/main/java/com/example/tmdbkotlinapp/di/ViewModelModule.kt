package com.example.tmdbkotlinapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tmdbkotlinapp.ui.random_movie.RandomMovieViewModel
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
}