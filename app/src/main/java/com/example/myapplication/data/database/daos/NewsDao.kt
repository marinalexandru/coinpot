package com.example.myapplication.data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.data.database.entities.NewsEntity

@Dao
interface NewsDao {
    @Query("SELECT * FROM news")
    fun getAll(): List<NewsEntity>

    @Insert
    fun insertAll(news: List<NewsEntity>)

    @Query("DELETE FROM news")
    fun clearAll()

}
