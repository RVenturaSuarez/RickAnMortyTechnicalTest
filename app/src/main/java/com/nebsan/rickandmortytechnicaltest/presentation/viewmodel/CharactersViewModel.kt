package com.nebsan.rickandmortytechnicaltest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.nebsan.rickandmortytechnicaltest.domain.usecase.GetCharactersFilteredByNameUseCase
import com.nebsan.rickandmortytechnicaltest.domain.usecase.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val getCharactersFilteredByNameUseCase: GetCharactersFilteredByNameUseCase,
) : ViewModel() {

    private val _characterName = MutableStateFlow("")
    val characterName: StateFlow<String> = _characterName

    val characters = characterName
        .debounce(300)
        .flatMapLatest { name ->
            if (name.isBlank()) {
                getCharactersUseCase()
            } else {
                getCharactersFilteredByNameUseCase(name)
            }
        }

    fun onCharacterNameChanged(newName: String) {
        _characterName.value = newName
    }
}