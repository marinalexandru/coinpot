package com.example.myapplication.data.repositories

import com.example.myapplication.data.memorycache.NewsMemoryCache
import com.example.myapplication.data.models.News
import io.reactivex.Observable
import com.revolut.rxdata.core.Data
import com.revolut.rxdata.dod.DataObservableDelegate
import io.reactivex.Single
import java.util.*
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsMemoryCache: NewsMemoryCache
) : NewsRepository {

    private val newsDod: DataObservableDelegate<Any, List<News>> = DataObservableDelegate<Any, List<News>>(
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
                id = "1",
                cover = "https://academy-public.coinmarketcap.com/srd-optimized-uploads/76166a01f3f344088ca6c4c71649aa98.jpeg",
                createdAt = Date(),
                releasedAt = Date(),
                title = "What is Halving?",
                subtitle = "Halving is the event where the supply of new BTC entering the market is programmatically halved. This is a built-in feature of Bitcoin's protocol that occurs every 210,000 blocks, or roughly every four years.",
                type = "alexandria",
                sourceName = "Connor Sephton",
                sourceUrl = "https://coinmarketcap.com/community/articles/648e18f0e186a85d04cb3ace/",

            ),
            News(
                id = "2",
                cover = "https://academy-public.coinmarketcap.com/srd-optimized-uploads/325813f7a9634aedbdb0a17e808b40b3.jpeg",
                createdAt = Date(),
                releasedAt = Date(),
                title = "Optimism Network Shows Signs of Hope Amidst Market Challenges",
                subtitle = "Recent updates and developments on the Optimism network, shared via their official Twitter account, have stirred positive sentiments within the community. The integration of creative platform manifold.xyz into the Optimism platform is seen as a positive development for NFT creators.",
                type = "alexandria",
                sourceName = "Connor Sephton",
                sourceUrl = "https://coinmarketcap.com/community/articles/648e0e64e215c15476fdedc3/",
            ),
            News(
                id = "3",
                cover = "https://academy-public.coinmarketcap.com/srd-optimized-uploads/bb2a713db8b048cab60e013a655953f6.jpeg",
                createdAt = Date(),
                releasedAt = Date(),
                title = "Polygon Proposes New Ecosystem Council",
                subtitle = "Polygon, the renowned Ethereum scaling solution, recently proposed a new decentralized governance model aimed at enhancing its network's security, upgradeability, and robustness.",
                type = "alexandria",
                sourceName = "Connor Sephton",
                sourceUrl = "https://coinmarketcap.com/community/articles/648e1ff8e215c15476fdeddf/"
            )
        )
    }

}
