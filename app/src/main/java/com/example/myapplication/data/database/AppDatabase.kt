package com.example.myapplication.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.data.database.daos.NewsDao
import com.example.myapplication.data.database.daos.TokenDao
import com.example.myapplication.data.database.entities.NewsEntity
import com.example.myapplication.data.database.entities.TokenEntity

@Database(entities = [TokenEntity::class, NewsEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
    abstract fun tokenDao(): TokenDao

    companion object {
        fun factory(context: Context) =
            Room.databaseBuilder(
                context,
                AppDatabase::class.java, Constants.DB_NAME
            ).build()
    }

}
