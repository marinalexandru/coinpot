package com.example.myapplication.data.api.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsResponse(
    @field:Json(name = "id")
    val id: String,
    @field:Json(name = "cover")
    val cover: String,
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "subtitle")
    val subtitle: String,
    @field:Json(name = "type")
    val type: String,
    @field:Json(name = "sourceName")
    val sourceName: String,
    @field:Json(name = "sourceUrl")
    val sourceUrl: String
)
