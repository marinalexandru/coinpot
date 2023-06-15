package com.example.myapplication.data.memorycache


import com.example.myapplication.data.models.TokenMetadata
import javax.inject.Inject

class TokenMetadataMemoryCacheImpl @Inject constructor() : TokenMetadataMemoryCache {

    private val cache: MutableList<TokenMetadata> = mutableListOf()

    override fun cache(list: List<TokenMetadata>) {
        cache.clear()
        cache.addAll(list)
    }

    override fun get(): List<TokenMetadata> = cache

    override fun clear() {
        cache.clear()
    }
}

