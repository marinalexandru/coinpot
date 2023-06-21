package com.example.myapplication.data.repositories

import com.example.myapplication.data.api.NewsAPIService
import com.example.myapplication.data.database.daos.NewsDao
import com.example.myapplication.data.memorycache.NewsMemoryCache
import com.example.myapplication.data.models.News
import com.revolut.rxdata.core.Data
import com.revolut.rxdata.dod.DataObservableDelegate
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsMemoryCache: NewsMemoryCache,
    private val newsAPIService: NewsAPIService,
    private val newsRepositoryMapper: NewsRepositoryMapper,
    private val newsDao: NewsDao
) : NewsRepository {

    private val newsDod: DataObservableDelegate<Any, List<News>> = DataObservableDelegate(
        fromNetwork = {
            Single.fromObservable(
                newsAPIService.getNews().map {
                    if (it.isSuccessful) {
                        return@map newsRepositoryMapper.mapResponse(it.body() ?: emptyList())
                    } else {
                        emptyList()
                    }
                }
            )
        },
        fromMemory = {
            newsMemoryCache.get()
        },
        toMemory = { _, news ->
            newsMemoryCache.cache(news)
        },
        fromStorage = {
            newsRepositoryMapper.fromEntity(newsDao.getAll())
        },
        toStorage = { _, news ->
            val entities = newsRepositoryMapper.toEntity(news)
            newsDao.clearAll()
            newsDao.insertAll(entities)
        }
    )

    override fun observe(forceReload: Boolean): Observable<Data<List<News>>> {
        return newsDod.observe(Unit, forceReload = forceReload)
    }

    override fun reload(): Completable {
        newsDod.updateMemory(Unit, emptyList())
        newsDod.notifyFromMemory(loading = true, where = { true })
        return newsDod.reload(Unit)
    }

}
