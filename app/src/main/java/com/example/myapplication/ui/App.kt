package com.example.myapplication.ui

import android.app.Application
import com.example.myapplication.di.components.AppComponent
import com.example.myapplication.di.components.DaggerAppComponent

class App: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.create()
    }

}

