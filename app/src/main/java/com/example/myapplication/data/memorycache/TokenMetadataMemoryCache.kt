package com.example.myapplication.data.memorycache

import com.example.myapplication.data.models.TokenMetadata

interface TokenMetadataMemoryCache {
    fun cache(list: List<TokenMetadata>)

    fun get(): List<TokenMetadata>

    fun clear()
}
