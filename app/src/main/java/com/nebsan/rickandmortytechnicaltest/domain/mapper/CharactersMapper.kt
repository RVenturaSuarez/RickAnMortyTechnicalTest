package com.nebsan.rickandmortytechnicaltest.domain.mapper

import com.nebsan.rickandmortytechnicaltest.data.remote.dto.CharacterDto
import com.nebsan.rickandmortytechnicaltest.domain.model.CharacterInfo

object CharactersMapper {

    fun CharacterDto.toDomain(): CharacterInfo {
        return CharacterInfo(id = id, name = name, image = image)
    }
}