package com.nebsan.rickandmortytechnicaltest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nebsan.rickandmortytechnicaltest.domain.usecase.GetCharacterDetailInfoUseCase
import com.nebsan.rickandmortytechnicaltest.presentation.ui.charactersDetail.CharacterDetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterDetailInfoUseCase: GetCharacterDetailInfoUseCase,
) : ViewModel() {

    private val _characterDetailState =
        MutableStateFlow<CharacterDetailUiState>(CharacterDetailUiState.Loading)
    val characterDetailState: StateFlow<CharacterDetailUiState> = _characterDetailState

    fun getInfoCharacter(characterId: Int) {
        viewModelScope.launch {
            _characterDetailState.value = CharacterDetailUiState.Loading

            val result = getCharacterDetailInfoUseCase(characterId)

            _characterDetailState.value = result.fold(
                onSuccess = { character ->
                    CharacterDetailUiState.Success(character)
                },
                onFailure = { error ->
                    CharacterDetailUiState.Error("Error: ${error.message}")
                }
            )
        }
    }

    fun clearInfoCharacter() {
        _characterDetailState.value = CharacterDetailUiState.Loading
    }
}