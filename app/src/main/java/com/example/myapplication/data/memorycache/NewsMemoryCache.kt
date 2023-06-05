package com.example.myapplication.data.memorycache

import com.example.myapplication.data.models.News

interface NewsMemoryCache {
    fun cache(list: List<News>)

    fun get(): List<News>

    fun clear()
}
