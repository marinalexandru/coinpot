package com.example.myapplication.di.modules

import com.example.myapplication.data.memorycache.NewsMemoryCache
import com.example.myapplication.data.memorycache.NewsMemoryCacheImpl
import com.example.myapplication.data.memorycache.TokenMetadataMemoryCache
import com.example.myapplication.data.memorycache.TokenMetadataMemoryCacheImpl
import dagger.Binds
import dagger.Module

@Module
abstract class CacheModule {

    @Binds
    abstract fun bindNewsMemoryCache(cache: NewsMemoryCacheImpl): NewsMemoryCache

    @Binds
    abstract fun bindTokenMetadataMemoryCache(cache: TokenMetadataMemoryCacheImpl): TokenMetadataMemoryCache

}
