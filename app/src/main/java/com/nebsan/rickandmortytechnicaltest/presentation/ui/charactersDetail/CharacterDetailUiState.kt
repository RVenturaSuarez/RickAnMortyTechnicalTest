package com.nebsan.rickandmortytechnicaltest.presentation.ui.charactersDetail

import com.nebsan.rickandmortytechnicaltest.domain.model.CharacterDetailInfo

sealed class CharacterDetailUiState {
    data object Loading : CharacterDetailUiState()
    data class Success(val character: CharacterDetailInfo) : CharacterDetailUiState()
    data class Error(val message: String) : CharacterDetailUiState()
}