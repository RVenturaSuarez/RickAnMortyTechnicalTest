package com.nebsan.rickandmortytechnicaltest.data.repository

import com.nebsan.rickandmortytechnicaltest.data.remote.CharactersApi
import com.nebsan.rickandmortytechnicaltest.data.remote.dto.CharacterDetailDto
import com.nebsan.rickandmortytechnicaltest.data.remote.dto.CharactersResponseDto
import com.nebsan.rickandmortytechnicaltest.domain.repository.CharactersRepository
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(private val charactersApi: CharactersApi) :
    CharactersRepository {

    override suspend fun getCharacters(): CharactersResponseDto {
        return charactersApi.getCharacters()
    }

    override suspend fun getCharacterDetailInfo(characterId: Int): CharacterDetailDto {
        return charactersApi.getCharacterDetailInfo(characterId)
    }
}