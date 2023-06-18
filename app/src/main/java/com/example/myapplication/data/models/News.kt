package com.example.myapplication.data.models

import java.util.*

data class News(
    val id: String,
    val cover: String,
    val title: String,
    val subtitle: String,
    val type: String,
    val sourceName: String,
    val sourceUrl: String
)
