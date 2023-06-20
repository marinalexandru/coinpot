package com.example.myapplication.data.repositories

import com.example.myapplication.data.api.dto.TokenResponse
import com.example.myapplication.data.database.entities.TokenEntity
import com.example.myapplication.data.models.Token
import javax.inject.Inject

class TokenRepositoryMapperImpl @Inject constructor() : TokenRepositoryMapper {
    override fun mapResponse(response: List<TokenResponse>): List<Token> {
        return response.map {
            Token(
                id = it.id,
                name = it.name,
                logo = it.logo,
                symbol = it.symbol,
                slug = it.slug,
                description = it.description
            )
        }
    }

    override fun fromEntity(entities: List<TokenEntity>): List<Token> {
        return entities.map {
            Token(
                id = it.id,
                name = it.name,
                logo = it.logo,
                symbol = it.symbol,
                slug = it.slug,
                description = it.description
            )
        }
    }

    override fun toEntity(models: List<Token>): List<TokenEntity> {
        return models.map {
            TokenEntity(
                id = it.id,
                name = it.name,
                logo = it.logo,
                symbol = it.symbol,
                slug = it.slug,
                description = it.description
            )
        }
    }

}
