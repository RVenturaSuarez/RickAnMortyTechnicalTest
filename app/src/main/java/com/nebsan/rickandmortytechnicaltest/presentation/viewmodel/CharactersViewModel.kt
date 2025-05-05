package com.nebsan.rickandmortytechnicaltest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nebsan.rickandmortytechnicaltest.domain.model.CharacterInfo
import com.nebsan.rickandmortytechnicaltest.domain.usecase.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(private val getCharactersUseCase: GetCharactersUseCase) :
    ViewModel() {

    var characters = MutableStateFlow<List<CharacterInfo>>(emptyList())
        private set

    init {
        getCharacters()
    }

    private fun getCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(1000L)
            characters.value = getCharactersUseCase.getCharacters()
        }
    }

}