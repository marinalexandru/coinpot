package com.example.myapplication.data.repositories

import com.example.myapplication.data.models.News
import com.revolut.rxdata.core.Data
import io.reactivex.Observable

interface NewsRepository {
    fun observeNews(forceReload: Boolean = false): Observable<Data<List<News>>>
}
