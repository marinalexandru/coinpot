package com.example.myapplication.data.repositories

import com.example.myapplication.data.api.dto.NewsResponse
import com.example.myapplication.data.database.entities.NewsEntity
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

    override fun fromEntity(entities: List<NewsEntity>): List<News> {
        return entities.map {
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

    override fun toEntity(models: List<News>): List<NewsEntity> {
        return models.map {
            NewsEntity(
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

}
