package com.nebsan.rickandmortytechnicaltest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.nebsan.rickandmortytechnicaltest.domain.model.CharacterInfo
import com.nebsan.rickandmortytechnicaltest.domain.usecase.GetCharacterDetailInfoUseCase
import com.nebsan.rickandmortytechnicaltest.domain.usecase.GetCharactersUseCase
import com.nebsan.rickandmortytechnicaltest.presentation.ui.charactersDetail.CharacterDetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val getCharacterDetailInfoUseCase: GetCharacterDetailInfoUseCase,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) :
    ViewModel() {

    val characters: Flow<PagingData<CharacterInfo>> =
        getCharactersUseCase.getCharacters()

    private val _characterDetailState =
        MutableStateFlow<CharacterDetailUiState>(CharacterDetailUiState.Loading)
    val characterDetailState: StateFlow<CharacterDetailUiState> = _characterDetailState


    fun getInfoCharacter(characterId: Int) {
        viewModelScope.launch(ioDispatcher) {
            _characterDetailState.value = CharacterDetailUiState.Loading

            val result = getCharacterDetailInfoUseCase.getCharacterDetailInfo(characterId)

            _characterDetailState.value = result.fold(
                onSuccess = { character ->
                    CharacterDetailUiState.Success(character)
                },
                onFailure = { error ->
                    CharacterDetailUiState.Error("Error loading character: ${error.message}")
                }
            )
        }
    }

    fun clearInfoCharacter() {
        _characterDetailState.value = CharacterDetailUiState.Loading
    }

}