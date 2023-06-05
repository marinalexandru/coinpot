package com.example.myapplication.data.repositories

import com.example.myapplication.data.memorycache.NewsMemoryCache
import com.example.myapplication.data.models.News
import io.reactivex.Observable
import com.example.myapplication.data.models.NewsAsset
import com.revolut.rxdata.core.Data
import com.revolut.rxdata.dod.DataObservableDelegate
import io.reactivex.Single
import java.util.*
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsMemoryCache: NewsMemoryCache
) : NewsRepository {

    private val newsDod: DataObservableDelegate<Any, List<News>> = DataObservableDelegate(
        fromNetwork = {
            Single.just(getNews())
        },
        fromMemory = {
            newsMemoryCache.get()
        },
        toMemory = { _, news ->
            newsMemoryCache.cache(news)
        },
        fromStorage = {
            getNews()
        },

        toStorage = { _, _ ->

        }
    )

    override fun observeNews(forceReload: Boolean): Observable<Data<List<News>>> {
        return newsDod.observe(Unit, forceReload = true)
    }

    private fun getNews(): List<News> {
        return listOf(
            News(
                cover = "https://academy-public.coinmarketcap.com/optimized-uploads/0aec0502868046419ceace229f92601f.gif",
                createdAt = Date(),
                releasedAt = Date(),
                title = "Article Title",
                subtitle = "Article Subtitle",
                type = "alexandria",
                sourceName = "Connor Sephton",
                sourceUrl = "https://coinmarketcap.com/alexandria/article/coinmarketcap-news-august-9-u-s-comes-for-tornado-cash",
                assets = listOf(
                    NewsAsset(
                        id = 1027,
                        name = "Ethereum",
                        symbol = "ETH",
                        slug = "ethereum"
                    )
                )
            )
        )
    }

}
