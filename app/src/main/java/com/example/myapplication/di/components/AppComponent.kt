package com.example.myapplication.di.components

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
interface AppComponent {

    val rootFlowComponent: RootFlowComponent.Builder

}
