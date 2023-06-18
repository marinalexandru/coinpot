package com.example.myapplication.data.repositories

import com.example.myapplication.data.api.dto.TokenResponse
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

}
