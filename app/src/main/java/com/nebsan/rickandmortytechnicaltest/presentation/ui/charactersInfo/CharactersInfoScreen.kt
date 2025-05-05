package com.nebsan.rickandmortytechnicaltest.presentation.ui.charactersInfo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nebsan.rickandmortytechnicaltest.presentation.ui.charactersInfo.components.CharactersList
import com.nebsan.rickandmortytechnicaltest.presentation.ui.charactersInfo.components.TopBarCharacters
import com.nebsan.rickandmortytechnicaltest.presentation.viewmodel.CharactersViewModel

@Composable
fun CharactersInfoScreen(
    charactersViewModel: CharactersViewModel = hiltViewModel(),
    onDetailCharacter: (Int) -> Unit,
) {

    val characters = charactersViewModel.characters.collectAsState().value
    var characterName by rememberSaveable { mutableStateOf("") }

    Scaffold(topBar = { TopBarCharacters() }) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            CharactersList(
                characters = characters,
                onDetailCharacter = { characterId -> onDetailCharacter(characterId) },
                modifier = Modifier.padding(horizontal = 20.dp)
            )
        }
    }
}



