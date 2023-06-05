package com.example.myapplication.data.memorycache

import com.example.myapplication.data.models.News
import javax.inject.Inject

class NewsMemoryCacheImpl @Inject constructor() : NewsMemoryCache {

    private val cache: MutableList<News> = mutableListOf()

    override fun cache(list: List<News>) {
        cache.clear()
        cache.addAll(list)
    }

    override fun get(): List<News> = cache

    override fun clear() {
        cache.clear()
    }
}
