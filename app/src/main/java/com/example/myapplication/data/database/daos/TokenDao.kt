package com.example.myapplication.data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.data.database.entities.TokenEntity

@Dao
interface TokenDao {
    @Query("SELECT * FROM tokens")
    fun getAll(): List<TokenEntity>

    @Insert
    fun insertAll(tokens: List<TokenEntity>)

    @Query("DELETE FROM tokens")
    fun clearAll()

}
