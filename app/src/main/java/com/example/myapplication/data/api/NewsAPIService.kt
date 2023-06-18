package com.example.myapplication.data.api

import co.infinum.retromock.meta.Mock
import co.infinum.retromock.meta.MockResponse
import com.example.myapplication.data.api.dto.NewsResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface NewsAPIService {

    @GET("news")
    @Mock
    @MockResponse(body = "news_response.json")
    fun getNews(
        @Header("Content-Type") contentType: String = "application/json"
    ): Observable<Response<List<NewsResponse>>>

}
