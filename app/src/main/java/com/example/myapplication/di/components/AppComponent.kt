package com.example.myapplication.di.components

import com.example.myapplication.di.modules.CacheModule
import com.example.myapplication.di.modules.RepositoryModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class, CacheModule::class])
interface AppComponent {

    val rootFlowComponent: RootFlowComponent.Builder

}
