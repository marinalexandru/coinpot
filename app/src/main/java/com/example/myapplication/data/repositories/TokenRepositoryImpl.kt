package com.example.myapplication.data.repositories

import com.example.myapplication.data.memorycache.TokenMemoryCache
import com.example.myapplication.data.models.Token
import com.revolut.rxdata.core.Data
import com.revolut.rxdata.dod.DataObservableDelegate
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val tokenMetadataMemoryCache: TokenMemoryCache
) : TokenRepository {
    private val tokenDod: DataObservableDelegate<Any, List<Token>> = DataObservableDelegate(
        fromNetwork = {
            Single.just(getTokens())
        },
        fromMemory = {
            tokenMetadataMemoryCache.get()
        },
        toMemory = { _, news ->
            tokenMetadataMemoryCache.cache(news)
        },
        fromStorage = {
            getTokens()
        },

        toStorage = { _, _ ->

        }
    )

    override fun observeTokens(forceReload: Boolean): Observable<Data<List<Token>>> {
        return tokenDod.observe(Unit, forceReload = true)
    }

    private fun getTokens(): List<Token> {
        return listOf(
            Token(
                id = "1",
                logo = "https://s2.coinmarketcap.com/static/img/coins/128x128/1.png",
                name = "Bitcoin",
                symbol = "BTC",
                slug = "bitcoin",
                description = "Bitcoin (BTC) is a consensus network that enables a new payment system and a completely digital currency. Powered by its users, it is a peer to peer payment network that requires no central authority to operate. On October 31st, 2008, an individual or group of individuals operating under the pseudonym Satoshi Nakamoto published the Bitcoin Whitepaper and described it as: a purely peer -to - peer version of electronic cash would allow online payments to be sent directly from one party to another without going through a financial institution "
            ),
            Token(
                id = "1027",
                logo = "https://s2.coinmarketcap.com/static/img/coins/128x128/1027.png",
                name = "Ethereum",
                symbol = "ETH",
                slug = "ethereum",
                description = "Ethereum (ETH) is a smart contract platform that enables developers to build decentralized applications (dapps) conceptualized by Vitalik Buterin in 2013. ETH is the native currency for the Ethereum platform and also works as the transaction fees to miners on the Ethereum network."
            ),
            Token(
                id = "825",
                logo = "https://s2.coinmarketcap.com/static/img/coins/128x128/825.png",
                name = "Tether",
                symbol = "USDT",
                slug = "tether",
                description = "Tether (USDT) is a cryptocurrency with a value meant to mirror the value of the U.S. dollar. The idea was to create a stable cryptocurrency that can be used like digital dollars. Coins that serve this purpose of being a stable dollar substitute are called “stable coins.” Tether is the most popular stable coin and even acts as a dollar replacement on many popular exchanges! According to their site, Tether converts cash into digital currency, to anchor or tether the value of the coin to the price of national currencies like the US dollar, the Euro, and the Yen. Like other cryptos it uses blockchain. Unlike other cryptos, it is [according to the official Tether site](https://tether.to/faqs/) “100% backed by USD” (USD is held in reserve). The primary use of Tether is that it offers some stability to the otherwise volatile crypto space and offers liquidity to exchanges who can’t deal in dollars and with banks (for example to the sometimes controversial but leading exchange Bitfinex)."
            ),
            Token(
                id = "3408",
                logo = "https://s2.coinmarketcap.com/static/img/coins/128x128/3408.png",
                name = "USD Coin",
                symbol = "USDC",
                slug = "usd-coin",
                description = "USD Coin (USDC) is a stablecoin fully backed by the US dollar and developed by the CENTRE consortium. Coinbase customers with US dollar accounts may exchange 1 USDC for US$1.00 (and vice versa) on Coinbase in jurisdictions where USDC support is available. The graph above reflects USDC’s current and historical redemption value of US$1.00, which may not match the price of USDC on other exchanges. USDC is an Ethereum token and is the only fiat-backed stablecoin currently supported by Coinbase. USDC is designed to minimize price volatility and it does so by ensuring that every unit of USDC is only created when a corresponding US dollar is deposited into a reserve bank account. Its major application at this point is as a mechanism for trading and hedging in global crypto capital markets. USDC can be used as a medium of exchange or as a store of value."
            ),
            Token(
                id = "1839",
                logo = "https://s2.coinmarketcap.com/static/img/coins/128x128/1839.png",
                name = "Binance Coin",
                symbol = "BNB",
                slug = "binance-coin",
                description = "Binance Coin (BNB) is an exchange-based token created and issued by the cryptocurrency exchange Binance. Initially created on the Ethereum blockchain as an ERC-20 token in July 2017, BNB was migrated over to Binance Chain in February 2019 and became the native coin of the Binance Chain. As of September 2019, BNB has a market cap of $2.3 billion and is one of the top 10 biggest cryptocurrencies in the world."
            ),
            Token(
                id = "2010",
                logo = "https://s2.coinmarketcap.com/static/img/coins/128x128/2010.png",
                name = "Cardano",
                symbol = "ADA",
                slug = "cardano",
                description = "Cardano (ADA) is a decentralized platform that will allow complex programmable transfers of value in a secure and scalable fashion. Cardano is one of the first blockchains to be built in the highly secure Haskell programming language. Cardano is developing a smart contract platform which seeks to deliver more advanced features than any protocol previously developed. It is the first blockchain platform to evolve out of a scientific philosophy and a research-first driven approach. The development team consists of a large global collective of expert engineers and researchers."
            )
        )
    }
}
