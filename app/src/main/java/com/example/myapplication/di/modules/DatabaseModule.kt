package com.example.myapplication.di.modules

import android.content.Context
import com.example.myapplication.data.database.AppDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        context: Context
    ): AppDatabase = AppDatabase.factory(context)

    @Provides
    @Singleton
    fun provideNewsDao(
        database: AppDatabase
    ) = database.newsDao()

    @Provides
    @Singleton
    fun provideTokenDao(
        database: AppDatabase
    ) = database.tokenDao()

}
