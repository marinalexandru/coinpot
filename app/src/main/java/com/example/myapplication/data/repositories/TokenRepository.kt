package com.example.myapplication.data.repositories

import com.example.myapplication.data.models.Token
import com.revolut.rxdata.core.Data
import io.reactivex.Observable

interface TokenRepository {
    fun observeTokens(forceReload: Boolean = true): Observable<Data<List<Token>>>
}
