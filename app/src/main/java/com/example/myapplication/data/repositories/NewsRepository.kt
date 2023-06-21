package com.example.myapplication.data.repositories

import com.example.myapplication.data.models.News
import com.revolut.rxdata.core.Data
import io.reactivex.Completable
import io.reactivex.Observable

interface NewsRepository {
    fun observe(forceReload: Boolean = false): Observable<Data<List<News>>>
    fun reload(): Completable
}
