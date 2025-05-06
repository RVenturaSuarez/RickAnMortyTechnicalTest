package com.nebsan.rickandmortytechnicaltest.domain.usecase

import android.util.Log
import com.nebsan.rickandmortytechnicaltest.domain.mapper.CharactersMapper.toDomain
import com.nebsan.rickandmortytechnicaltest.domain.model.CharacterDetailInfo
import com.nebsan.rickandmortytechnicaltest.domain.repository.CharactersRepository
import javax.inject.Inject

class GetCharacterDetailInfoUseCase @Inject constructor(private val charactersRepository: CharactersRepository) {

    suspend fun getCharacterDetailInfo(characterId: Int): CharacterDetailInfo {
        val characterDto = charactersRepository.getCharacterDetailInfo(characterId = characterId)
        Log.d("NebsanDev", characterDto.toString())
        return characterDto.toDomain()
    }
}