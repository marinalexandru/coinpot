package com.example.myapplication.data.memorycache

import com.example.myapplication.data.models.Token

interface TokenMemoryCache {
    fun cache(list: List<Token>)

    fun get(): List<Token>

    fun clear()
}
