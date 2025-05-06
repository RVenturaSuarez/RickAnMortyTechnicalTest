package com.nebsan.rickandmortytechnicaltest.domain.mapper

import com.nebsan.rickandmortytechnicaltest.data.remote.dto.CharacterDetailDto
import com.nebsan.rickandmortytechnicaltest.data.remote.dto.CharacterDto
import com.nebsan.rickandmortytechnicaltest.domain.model.CharacterDetailInfo
import com.nebsan.rickandmortytechnicaltest.domain.model.CharacterInfo

object CharactersMapper {

    fun CharacterDto.toDomain(): CharacterInfo {
        return CharacterInfo(id = id, name = name, image = image)
    }

    fun CharacterDetailDto.toDomain(): CharacterDetailInfo {
        return CharacterDetailInfo(
            name = name,
            status = status,
            specie = specie,
            gender = gender,
            image = image,
            episodes = episodes.mapNotNull { episodeUrl ->
                episodeUrl.substringAfterLast("/").toIntOrNull()
            }
        )
    }
}