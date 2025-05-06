package com.nebsan.rickandmortytechnicaltest.domain.repository

import androidx.paging.PagingData
import com.nebsan.rickandmortytechnicaltest.data.remote.dto.CharacterDetailDto
import com.nebsan.rickandmortytechnicaltest.data.remote.dto.CharacterDto
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    fun getCharacters(): Flow<PagingData<CharacterDto>>
    suspend fun getCharacterDetailInfo(characterId: Int): Result<CharacterDetailDto>
}