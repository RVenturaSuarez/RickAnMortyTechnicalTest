package com.nebsan.rickandmortytechnicaltest.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.nebsan.rickandmortytechnicaltest.domain.mapper.CharactersMapper.toDomain
import com.nebsan.rickandmortytechnicaltest.domain.model.CharacterInfo
import com.nebsan.rickandmortytechnicaltest.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(private val charactersRepository: CharactersRepository) {

    fun getCharacters(): Flow<PagingData<CharacterInfo>> {
        return charactersRepository.getCharacters()
            .map { pagingData ->
                pagingData.map { characterDto -> characterDto.toDomain() }
            }
    }
}