package com.example.myapplication.data.repositories

import com.example.myapplication.data.api.dto.NewsResponse
import com.example.myapplication.data.models.News

interface NewsRepositoryMapper {

    fun mapResponse(response: List<NewsResponse>): List<News>

}
