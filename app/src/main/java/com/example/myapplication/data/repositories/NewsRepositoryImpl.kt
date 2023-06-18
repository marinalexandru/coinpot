package com.example.myapplication.data.repositories

import com.example.myapplication.data.api.NewsAPIService
import com.example.myapplication.data.memorycache.NewsMemoryCache
import com.example.myapplication.data.models.News
import com.revolut.rxdata.core.Data
import com.revolut.rxdata.dod.DataObservableDelegate
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsMemoryCache: NewsMemoryCache,
    private val newsAPIService: NewsAPIService,
    private val newsRepositoryMapper: NewsRepositoryMapper
) : NewsRepository {

    private val newsDod: DataObservableDelegate<Any, List<News>> = DataObservableDelegate(
        fromNetwork = {
            try {
                Single.fromObservable(
                    newsAPIService.getNews().map {
                        if (it.isSuccessful) {
                            return@map newsRepositoryMapper.mapResponse(it.body() ?: emptyList())
                        } else {
                            emptyList()
                        }
                    }
                )
            } catch (e: Exception) {
                Single.error(e)
            }
        },
        fromMemory = {
            newsMemoryCache.get()
        },
        toMemory = { _, news ->
            newsMemoryCache.cache(news)
        },
        fromStorage = { null },
        toStorage = { _, _ -> }
    )

    override fun observeNews(forceReload: Boolean): Observable<Data<List<News>>> {
        return newsDod.observe(Unit, forceReload = forceReload)
    }

}
