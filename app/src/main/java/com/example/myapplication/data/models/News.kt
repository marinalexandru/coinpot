package com.example.myapplication.data.models

import java.util.*

data class News(
    val cover: String,
    val assets: List<NewsAsset>,
    val createdAt: Date,
    val releasedAt: Date,
    val title: String,
    val subtitle: String,
    val type: String,
    val sourceName: String,
    val sourceUrl: String
)
