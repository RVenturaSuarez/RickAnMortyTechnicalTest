package com.nebsan.rickandmortytechnicaltest.presentation.viewmodel

import com.nebsan.rickandmortytechnicaltest.domain.model.CharacterDetailInfo
import com.nebsan.rickandmortytechnicaltest.domain.usecase.GetCharacterDetailInfoUseCase
import com.nebsan.rickandmortytechnicaltest.domain.usecase.GetCharactersUseCase
import com.nebsan.rickandmortytechnicaltest.presentation.ui.charactersDetail.CharacterDetailUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class CharactersViewModelTest {

    private lateinit var viewModel: CharactersViewModel
    private val getCharactersUseCase = mock<GetCharactersUseCase>()
    private val getCharacterDetailInfoUseCase = mock<GetCharacterDetailInfoUseCase>()
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        viewModel =
            CharactersViewModel(getCharactersUseCase, getCharacterDetailInfoUseCase, dispatcher)
    }


    @Test
    fun `getInfoCharacter update state to Success when UseCase returns data`() = runTest {
        // GIVEN
        val characterId = 1
        val characterDetailInfo = CharacterDetailInfo(
            name = "Rick Sanchez",
            status = "Alive",
            specie = "Human",
            gender = "Male",
            image = "https://rick.com/rick.png",
            episodes = listOf(1, 2)
        )
        val result = Result.success(characterDetailInfo)

        whenever(getCharacterDetailInfoUseCase.getCharacterDetailInfo(characterId)).thenReturn(
            result
        )

        // WHEN
        viewModel.getInfoCharacter(characterId)
        advanceUntilIdle()

        // THEN
        assertTrue(viewModel.characterDetailState.value is CharacterDetailUiState.Success)
        assertEquals(
            (viewModel.characterDetailState.value as CharacterDetailUiState.Success).character,
            characterDetailInfo
        )
    }

    @Test
    fun `getInfoCharacter update state to Error when UseCase returns failure`() = runTest {
        // GIVEN
        val characterId = 1
        val result = Result.failure<CharacterDetailInfo>(Exception("Error loading character"))

        whenever(getCharacterDetailInfoUseCase.getCharacterDetailInfo(characterId)).thenReturn(
            result
        )

        // WHEN
        viewModel.getInfoCharacter(characterId)
        advanceUntilIdle()

        // THEN
        assertTrue(viewModel.characterDetailState.value is CharacterDetailUiState.Error)
        assertEquals(
            (viewModel.characterDetailState.value as CharacterDetailUiState.Error).message,
            "Error loading character: Error loading character"
        )
    }

    @Test
    fun `clearInfoCharacter set state to Loading`() = runTest {
        // WHEN
        viewModel.clearInfoCharacter()

        // THEN
        assertTrue(viewModel.characterDetailState.value is CharacterDetailUiState.Loading)
    }


}