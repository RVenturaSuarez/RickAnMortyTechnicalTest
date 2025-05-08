package com.nebsan.rickandmortytechnicaltest.domain.usecase

import com.nebsan.rickandmortytechnicaltest.domain.mapper.CharactersMapper.toDomain
import com.nebsan.rickandmortytechnicaltest.domain.model.CharacterDetailInfo
import com.nebsan.rickandmortytechnicaltest.domain.repository.CharactersRepository
import javax.inject.Inject

class GetCharacterDetailInfoUseCase @Inject constructor(private val charactersRepository: CharactersRepository) {

    suspend operator fun invoke(characterId: Int): Result<CharacterDetailInfo> {
        return charactersRepository.getCharacterDetailInfo(characterId).map { characterDetailDto ->
            characterDetailDto.toDomain()
        }
    }
}