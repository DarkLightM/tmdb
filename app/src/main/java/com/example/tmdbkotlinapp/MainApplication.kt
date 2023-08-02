package com.example.tmdbkotlinapp

import android.app.Application
import com.example.tmdbkotlinapp.di.AppComponent
import com.example.tmdbkotlinapp.di.DaggerAppComponent

class MainApplication: Application() {
    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}