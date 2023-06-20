package com.example.myapplication.di.modules

import com.example.myapplication.data.repositories.NewsRepositoryMapper
import com.example.myapplication.data.repositories.NewsRepositoryMapperImpl
import com.example.myapplication.data.repositories.TokenRepositoryMapper
import com.example.myapplication.data.repositories.TokenRepositoryMapperImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryMapperModule {

    @Binds
    abstract fun bindNewsRepositoryMapper(mapper: NewsRepositoryMapperImpl): NewsRepositoryMapper

    @Binds
    abstract fun bindTokenRepositoryMapper(mapper: TokenRepositoryMapperImpl): TokenRepositoryMapper
}
