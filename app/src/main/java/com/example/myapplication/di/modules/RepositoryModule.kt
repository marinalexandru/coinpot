package com.example.myapplication.di.modules

import com.example.myapplication.data.repositories.NewsRepository
import com.example.myapplication.data.repositories.NewsRepositoryImpl
import com.example.myapplication.data.repositories.NewsRepositoryMapper
import com.example.myapplication.data.repositories.NewsRepositoryMapperImpl
import com.example.myapplication.data.repositories.TokenRepository
import com.example.myapplication.data.repositories.TokenRepositoryImpl
import com.example.myapplication.data.repositories.TokenRepositoryMapper
import com.example.myapplication.data.repositories.TokenRepositoryMapperImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindNewsRepository(repository: NewsRepositoryImpl): NewsRepository

    @Binds
    abstract fun bindNewsRepositoryMapper(mapper: NewsRepositoryMapperImpl): NewsRepositoryMapper

    @Binds
    abstract fun bindTokenRepository(repository: TokenRepositoryImpl): TokenRepository

    @Binds
    abstract fun bindTokenRepositoryMapper(mapper: TokenRepositoryMapperImpl): TokenRepositoryMapper
}
