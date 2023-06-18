package com.example.myapplication.data.repositories

import com.example.myapplication.data.api.dto.TokenResponse
import com.example.myapplication.data.models.Token

interface TokenRepositoryMapper {

    fun mapResponse(response: List<TokenResponse>): List<Token>

}
