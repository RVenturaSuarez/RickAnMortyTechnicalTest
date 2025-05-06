package com.nebsan.rickandmortytechnicaltest.domain.usecase

import com.nebsan.rickandmortytechnicaltest.data.remote.dto.CharacterDetailDto
import com.nebsan.rickandmortytechnicaltest.domain.model.CharacterDetailInfo
import com.nebsan.rickandmortytechnicaltest.domain.repository.CharactersRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever


class GetCharacterDetailInfoUseCaseTest {

    private lateinit var getCharacterDetailInfoUseCase: GetCharacterDetailInfoUseCase
    private lateinit var charactersRepository: CharactersRepository

    @Before
    fun setUp() {
        charactersRepository = mock()
        getCharacterDetailInfoUseCase = GetCharacterDetailInfoUseCase(charactersRepository)
    }


    @Test
    fun `returns success when repository returns valid CharacterDetailDto`() = runTest {
        // GIVEN
        val characterId = 1
        val characterDetailDto = CharacterDetailDto(
            name = "Rick Sanchez",
            status = "Alive",
            specie = "Human",
            gender = "Male",
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            episodes = listOf("https://rickandmortyapi.com/api/episode/1")
        )

        val expected = CharacterDetailInfo(
            name = "Rick Sanchez",
            status = "Alive",
            specie = "Human",
            gender = "Male",
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            episodes = listOf(1)
        )

        // WHEN
        whenever(charactersRepository.getCharacterDetailInfo(characterId)).thenReturn(Result.success(characterDetailDto))

        // THEN
        val result = getCharacterDetailInfoUseCase.getCharacterDetailInfo(characterId)
        assertTrue(result.isSuccess)
        assertEquals(expected, result.getOrNull())
    }

    @Test
    fun `returns failure when repository throws error`() = runTest {
        // GIVEN
        val characterId = -1
        val exception = Exception("Character not found")


        // WHEN
        whenever(charactersRepository.getCharacterDetailInfo(characterId)).thenReturn(Result.failure(exception))
        val result = getCharacterDetailInfoUseCase.getCharacterDetailInfo(characterId)

        // THEN
        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }


}