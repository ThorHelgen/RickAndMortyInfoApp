package com.thorhelgen.rickandmortyinfoapp.presentation

import android.app.Application
import com.thorhelgen.rickandmortyinfoapp.di.AppComponent
import com.thorhelgen.rickandmortyinfoapp.di.DaggerAppComponent

class App : Application() {
    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.factory().create(this)
    }
}