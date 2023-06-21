package com.example.myapplication.data.repositories

import com.example.myapplication.data.models.Token
import com.revolut.rxdata.core.Data
import io.reactivex.Completable
import io.reactivex.Observable

interface TokenRepository {
    fun observe(forceReload: Boolean = true): Observable<Data<List<Token>>>

    fun reload(): Completable
}
