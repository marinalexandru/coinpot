package com.example.myapplication.di.modules

import com.example.myapplication.data.repositories.NewsRepository
import com.example.myapplication.data.repositories.NewsRepositoryImpl
import com.example.myapplication.data.repositories.TokenRepository
import com.example.myapplication.data.repositories.TokenRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindNewsRepository(repository: NewsRepositoryImpl): NewsRepository

    @Binds
    abstract fun bindTokenRepository(repository: TokenRepositoryImpl): TokenRepository
}
