package com.example.myapplication.data.repositories

import com.example.myapplication.data.api.dto.NewsResponse
import com.example.myapplication.data.models.News
import javax.inject.Inject

class NewsRepositoryMapperImpl @Inject constructor() : NewsRepositoryMapper {
    override fun mapResponse(response: List<NewsResponse>) = response.map {
        News(
            id = it.id,
            cover = it.cover,
            title = it.title,
            subtitle = it.subtitle,
            type = it.type,
            sourceName = it.sourceName,
            sourceUrl = it.sourceUrl
        )
    }

}
