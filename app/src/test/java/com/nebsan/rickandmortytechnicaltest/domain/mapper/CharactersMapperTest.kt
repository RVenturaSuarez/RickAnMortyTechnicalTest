package com.nebsan.rickandmortytechnicaltest.domain.mapper

import com.nebsan.rickandmortytechnicaltest.data.remote.dto.CharacterDetailDto
import com.nebsan.rickandmortytechnicaltest.data.remote.dto.CharacterDto
import com.nebsan.rickandmortytechnicaltest.domain.mapper.CharactersMapper.toDomain
import com.nebsan.rickandmortytechnicaltest.domain.model.CharacterDetailInfo
import com.nebsan.rickandmortytechnicaltest.domain.model.CharacterInfo
import org.junit.Assert.assertEquals
import org.junit.Test

class CharactersMapperTest {

    @Test
    fun `CharacterDto is correctly mapper to CharacterInfo`() {
        // GIVEN
        val characterDto = CharacterDto(
            id = 1, name = "Rick Sanchez", "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
        )

        val expectedCharacterInfo = CharacterInfo(
            id = 1, name = "Rick Sanchez", "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
        )

        // WHEN
        val characterInfo = characterDto.toDomain()

        // THEN
        assertEquals(characterInfo, expectedCharacterInfo)
    }


    @Test
    fun `CharacterDetailDto is correctly mapper to CharacterDetailInfo`() {
        // GIVEN
        val characterDetailDto = CharacterDetailDto(
            name = "Rick Sanchez",
            status = "Alive",
            specie = "Human",
            gender = "Male",
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            episodes = listOf(
                "https://rickandmortyapi.com/api/episode/1",
                "https://rickandmortyapi.com/api/episode/2"
            )
        )

        val expectedCharacterDetailInfo = CharacterDetailInfo(
            name = "Rick Sanchez",
            status = "Alive",
            specie = "Human",
            gender = "Male",
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            episodes = listOf(1, 2)
        )

        // WHEN
        val characterDetailInfo = characterDetailDto.toDomain()


        // THEN
        assertEquals(characterDetailInfo, expectedCharacterDetailInfo)
    }

}