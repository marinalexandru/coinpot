package com.example.myapplication.data.memorycache

import com.example.myapplication.data.models.News
import javax.inject.Inject

import java.util.concurrent.locks.ReentrantLock

class NewsMemoryCacheImpl @Inject constructor() : NewsMemoryCache {
    private val lock = ReentrantLock()
    private var cachedList: List<News> = emptyList()

    override fun cache(list: List<News>) {
        lock.lock()
        try {
            cachedList = list.toList() // Create a copy of the list
        } finally {
            lock.unlock()
        }
    }

    override fun get(): List<News> {
        lock.lock()
        try {
            return cachedList.toList() // Create a copy of the cached list to ensure immutability
        } finally {
            lock.unlock()
        }
    }

    override fun clear() {
        lock.lock()
        try {
            cachedList = emptyList()
        } finally {
            lock.unlock()
        }
    }
}
