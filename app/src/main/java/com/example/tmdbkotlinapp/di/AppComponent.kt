package com.example.tmdbkotlinapp.di

import com.example.tmdbkotlinapp.api.NetworkModule
import com.example.tmdbkotlinapp.ui.random_movie.RandomMovieFragment
import dagger.Component

@Component(modules = [AppBindModule::class, NetworkModule::class, ViewModelModule::class])
interface AppComponent {
    fun viewModelFactory() : ViewModelFactory
    fun inject(fragment: RandomMovieFragment)
}