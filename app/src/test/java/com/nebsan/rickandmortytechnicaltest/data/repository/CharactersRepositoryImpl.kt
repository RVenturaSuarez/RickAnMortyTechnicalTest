package com.nebsan.rickandmortytechnicaltest.data.repository

import com.nebsan.rickandmortytechnicaltest.data.remote.CharactersApi
import com.nebsan.rickandmortytechnicaltest.data.remote.dto.CharacterDetailDto
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class CharactersRepositoryImplTest {

    private lateinit var repository: CharactersRepositoryImpl
    private val charactersApi = mock<CharactersApi>() // Mock de la API
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        repository = CharactersRepositoryImpl(charactersApi)
    }


    @Test
    fun `getCharacterDetailInfo return success when api call is successful`() = runTest {
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

        // WHEN
        whenever(charactersApi.getCharacterDetailInfo(characterId)).thenReturn(
            characterDetailDto
        )
        val result = repository.getCharacterDetailInfo(characterId)

        // THEN
        assertTrue(result.isSuccess)
        assertEquals(result.getOrNull(), characterDetailDto)
    }

    @Test
    fun `getCharacterDetailInfo return failure when api call fails`() = runTest {
        // GIVEN
        val characterId = -1
        val exception = RuntimeException("Character not found")

        // WHEN
        whenever(charactersApi.getCharacterDetailInfo(characterId)).thenThrow(exception)
        val result = repository.getCharacterDetailInfo(characterId)

        // THEN
        assertTrue(result.isFailure)
        assertEquals(
            (result.exceptionOrNull() as RuntimeException).message,
            "Character not found"
        )
    }
}