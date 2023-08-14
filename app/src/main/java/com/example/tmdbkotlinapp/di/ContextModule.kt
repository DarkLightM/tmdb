package com.example.tmdbkotlinapp.di

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ContextModule(private val context: Context) {
    @Provides
    fun provideApplicationContext(): Context {
        return context.applicationContext
    }
}