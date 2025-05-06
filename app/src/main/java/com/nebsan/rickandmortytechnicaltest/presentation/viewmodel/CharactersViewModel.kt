package com.nebsan.rickandmortytechnicaltest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nebsan.rickandmortytechnicaltest.domain.usecase.GetCharacterDetailInfoUseCase
import com.nebsan.rickandmortytechnicaltest.domain.usecase.GetCharactersFilteredByNameUseCase
import com.nebsan.rickandmortytechnicaltest.domain.usecase.GetCharactersUseCase
import com.nebsan.rickandmortytechnicaltest.presentation.ui.charactersDetail.CharacterDetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val getCharacterDetailInfoUseCase: GetCharacterDetailInfoUseCase,
    private val getCharactersFilteredByNameUseCase: GetCharactersFilteredByNameUseCase,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) :
    ViewModel() {

    private val _characterName = MutableStateFlow("")
    val characterName: StateFlow<String> = _characterName

    val characters = characterName
        .debounce(300)
        .flatMapLatest { name ->
            if (name.isBlank()) {
                getCharactersUseCase.getCharacters()
            } else {
                getCharactersFilteredByNameUseCase.getCharactersFilteredByName(name)
            }
        }

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

    fun onCharacterNameChanged(newName: String) {
        _characterName.value = newName
    }

    fun clearInfoCharacter() {
        _characterDetailState.value = CharacterDetailUiState.Loading
    }

}