package com.example.myapplication.data.api

import co.infinum.retromock.meta.Mock
import co.infinum.retromock.meta.MockResponse
import com.example.myapplication.data.api.dto.TokenResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface TokensAPIService {

    @GET("tokens")
    @Mock
    @MockResponse(body = "tokens_response.json")
    fun getTokens(
        @Header("Content-Type") contentType: String = "application/json"
    ): Observable<Response<List<TokenResponse>>>

}
