package com.example.myapplication.data.memorycache


import com.example.myapplication.data.models.Token
import javax.inject.Inject

class TokenMemoryCacheImpl @Inject constructor() : TokenMemoryCache {

    private val cache: MutableList<Token> = mutableListOf()

    override fun cache(list: List<Token>) {
        cache.clear()
        cache.addAll(list)
    }

    override fun get(): List<Token> = cache

    override fun clear() {
        cache.clear()
    }
}

