package com.example.myapplication.di.modules

import com.example.myapplication.data.services.IntentDispatchService
import com.example.myapplication.data.services.IntentDispatchServiceImpl
import dagger.Binds
import dagger.Module

@Module
abstract class ServiceModule {

    @Binds
    abstract fun bindIntentDispatchService(service: IntentDispatchServiceImpl): IntentDispatchService

}
