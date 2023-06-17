package com.example.myapplication.di.modules

import com.example.myapplication.data.memorycache.NewsMemoryCache
import com.example.myapplication.data.memorycache.NewsMemoryCacheImpl
import com.example.myapplication.data.memorycache.TokenMemoryCache
import com.example.myapplication.data.memorycache.TokenMemoryCacheImpl
import dagger.Binds
import dagger.Module

@Module
abstract class CacheModule {

    @Binds
    abstract fun bindNewsMemoryCache(cache: NewsMemoryCacheImpl): NewsMemoryCache

    @Binds
    abstract fun bindTokenMemoryCache(cache: TokenMemoryCacheImpl): TokenMemoryCache

}
