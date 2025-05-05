package com.nebsan.rickandmortytechnicaltest.domain.usecase

import com.nebsan.rickandmortytechnicaltest.domain.mapper.CharactersMapper.toDomain
import com.nebsan.rickandmortytechnicaltest.domain.model.CharacterInfo
import com.nebsan.rickandmortytechnicaltest.domain.repository.CharactersRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(private val charactersRepository: CharactersRepository) {

    suspend fun getCharacters(): List<CharacterInfo> {
        val characters = charactersRepository.getCharacters().results
        return characters.map { characterDto ->
            characterDto.toDomain()
        }
    }
}