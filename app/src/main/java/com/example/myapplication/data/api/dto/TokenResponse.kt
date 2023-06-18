package com.example.myapplication.data.api.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TokenResponse(
    @field:Json(name = "id")
    val id: String,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "logo")
    val logo: String,
    @field:Json(name = "symbol")
    val symbol: String,
    @field:Json(name = "slug")
    val slug: String,
    @field:Json(name = "description")
    val description: String
)
