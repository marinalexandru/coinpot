package com.example.myapplication.data.memorycache


import com.example.myapplication.data.models.Token
import javax.inject.Inject
import java.util.concurrent.locks.ReentrantLock
class TokenMemoryCacheImpl @Inject constructor() : TokenMemoryCache {
    private val lock = ReentrantLock()
    private var cachedList: List<Token> = emptyList()

    override fun cache(list: List<Token>) {
        lock.lock()
        try {
            cachedList = list.toList() // Create a copy of the list
        } finally {
            lock.unlock()
        }
    }

    override fun get(): List<Token> {
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
