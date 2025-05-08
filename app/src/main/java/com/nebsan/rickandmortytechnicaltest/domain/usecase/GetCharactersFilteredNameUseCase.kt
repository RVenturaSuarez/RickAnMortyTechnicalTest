package com.nebsan.rickandmortytechnicaltest.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.nebsan.rickandmortytechnicaltest.domain.mapper.CharactersMapper.toDomain
import com.nebsan.rickandmortytechnicaltest.domain.model.CharacterInfo
import com.nebsan.rickandmortytechnicaltest.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCharactersFilteredByNameUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository,
) {
    operator fun invoke(characterName: String): Flow<PagingData<CharacterInfo>> {
        return charactersRepository.getCharacters(characterName)
            .map { pagingData ->
                pagingData.map { characterDto -> characterDto.toDomain() }
            }
    }
}