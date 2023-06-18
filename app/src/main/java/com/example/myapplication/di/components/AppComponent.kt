package com.example.myapplication.di.components

import android.app.Application
import android.content.Context
import com.example.myapplication.di.modules.AppModule
import com.example.myapplication.di.modules.CacheModule
import com.example.myapplication.di.modules.NetworkingModule
import com.example.myapplication.di.modules.RepositoryModule
import com.example.myapplication.di.modules.ServiceModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RepositoryModule::class,
        CacheModule::class,
        AppModule::class,
        ServiceModule::class,
        NetworkingModule::class
    ]
)
interface AppComponent {

    val rootFlowComponent: RootFlowComponent.Builder

    fun context(): Context

    fun applicationContext(): Application

}
