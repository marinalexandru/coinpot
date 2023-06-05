package com.example.myapplication.di.modules

import com.example.myapplication.data.repositories.NewsRepository
import com.example.myapplication.data.repositories.NewsRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindNewsRepository(repository: NewsRepositoryImpl): NewsRepository
}
