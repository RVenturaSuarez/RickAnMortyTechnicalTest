package com.nebsan.rickandmortytechnicaltest.domain.repository

import com.nebsan.rickandmortytechnicaltest.data.remote.dto.CharactersResponseDto

interface CharactersRepository {
    suspend fun getCharacters(): CharactersResponseDto
}