package com.example.myapplication.data.repositories

import com.example.myapplication.data.api.TokensAPIService
import com.example.myapplication.data.database.daos.TokenDao
import com.example.myapplication.data.memorycache.TokenMemoryCache
import com.example.myapplication.data.models.Token
import com.revolut.rxdata.core.Data
import com.revolut.rxdata.dod.DataObservableDelegate
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val tokenMemoryCache: TokenMemoryCache,
    private val tokensAPIService: TokensAPIService,
    private val tokenRepositoryMapper: TokenRepositoryMapper,
    private val tokenDao: TokenDao
) : TokenRepository {

    private val tokenDod: DataObservableDelegate<Any, List<Token>> = DataObservableDelegate(
        fromNetwork = {
            Single.fromObservable(
                tokensAPIService.getTokens().map {
                    if (it.isSuccessful) {
                        return@map tokenRepositoryMapper.mapResponse(it.body() ?: emptyList())
                    } else {
                        emptyList()
                    }
                }
            )
        },
        fromMemory = {
            tokenMemoryCache.get()
        },

        toMemory = { _, tokens ->
            tokenMemoryCache.cache(tokens)
        },

        fromStorage = {
            val entities = tokenDao.getAll()
            tokenRepositoryMapper.fromEntity(entities)
        },

        toStorage = { _, tokens ->
            val entities = tokenRepositoryMapper.toEntity(tokens)
            tokenDao.clearAll()
            tokenDao.insertAll(entities)
        }
    )

    override fun observe(forceReload: Boolean): Observable<Data<List<Token>>> {
        return tokenDod.observe(Unit, forceReload = forceReload)
    }

    override fun reload(): Completable {
        tokenDod.updateMemory(Unit, emptyList())
        tokenDod.notifyFromMemory(loading = true, where = { true })
        return tokenDod.reload(Unit)
    }

}
