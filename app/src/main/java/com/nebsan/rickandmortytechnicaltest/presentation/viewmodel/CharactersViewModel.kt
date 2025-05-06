package com.nebsan.rickandmortytechnicaltest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nebsan.rickandmortytechnicaltest.domain.model.CharacterDetailInfo
import com.nebsan.rickandmortytechnicaltest.domain.model.CharacterInfo
import com.nebsan.rickandmortytechnicaltest.domain.usecase.GetCharacterDetailInfoUseCase
import com.nebsan.rickandmortytechnicaltest.domain.usecase.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val getCharacterDetailInfoUseCase: GetCharacterDetailInfoUseCase,
) :
    ViewModel() {

    var characters = MutableStateFlow<List<CharacterInfo>>(emptyList())
        private set

    var selectedCharacter = MutableStateFlow<CharacterDetailInfo?>(null)
        private set

    init {
        getCharacters()
    }

    private fun getCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            characters.value = getCharactersUseCase.getCharacters()
        }
    }

    fun getInfoCharacter(characterId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            selectedCharacter.value =
                getCharacterDetailInfoUseCase.getCharacterDetailInfo(characterId)
        }
    }

    fun clearInfoCharacter() {
        selectedCharacter.value = null
    }

}