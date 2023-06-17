package com.example.myapplication.data.models

import java.util.*

data class News(
    val id: String,
    val cover: String,
    val createdAt: Date,
    val releasedAt: Date,
    val title: String,
    val subtitle: String,
    val type: String,
    val sourceName: String,
    val sourceUrl: String
)
