package com.example.myapplication.data.repositories

import com.example.myapplication.data.models.TokenMetadata
import com.revolut.rxdata.core.Data
import io.reactivex.Observable

interface TokenMetadataRepository {
    fun observeTokens(forceReload: Boolean = true): Observable<Data<List<TokenMetadata>>>
}
