package com.example.myapplication.di.modules

import android.content.Context
import co.infinum.retromock.Retromock
import com.example.myapplication.BuildConfig
import com.example.myapplication.data.api.NewsAPIService
import com.example.myapplication.data.api.TokensAPIService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class NetworkingModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient =
        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()
        } else {
            OkHttpClient.Builder().build()
        }

    @Singleton
    @Provides
    fun provideNetworkClient(
        okHttpClient: OkHttpClient,
        moshi: Moshi,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .build()

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Singleton
    @Provides
    fun provideNetworkMock(
        retrofit: Retrofit,
        context: Context
    ): Retromock =
        Retromock.Builder()
            .defaultBodyFactory(context.assets::open)
            .retrofit(retrofit)
            .build()

    @Singleton
    @Provides
    fun provideNewsAPIService(
        // For demo purposes, we will use Retromock instead of Retrofit
        retrofit: Retromock
    ): NewsAPIService = retrofit.create(NewsAPIService::class.java)

    @Singleton
    @Provides
    fun provideTokensAPIService(
        // For demo purposes, we will use Retromock instead of Retrofit
        retrofit: Retromock
    ): TokensAPIService = retrofit.create(TokensAPIService::class.java)

}
