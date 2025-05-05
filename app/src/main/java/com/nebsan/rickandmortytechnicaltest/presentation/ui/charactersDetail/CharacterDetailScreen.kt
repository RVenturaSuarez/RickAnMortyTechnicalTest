package com.nebsan.rickandmortytechnicaltest.presentation.ui.charactersDetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nebsan.rickandmortytechnicaltest.presentation.ui.charactersDetail.components.CharacterAttributeSection
import com.nebsan.rickandmortytechnicaltest.presentation.ui.charactersDetail.components.EpisodesSection
import com.nebsan.rickandmortytechnicaltest.presentation.ui.charactersDetail.components.TopBarDetailCharacter
import com.nebsan.rickandmortytechnicaltest.presentation.ui.core.components.CharacterImageSection
import com.nebsan.rickandmortytechnicaltest.presentation.viewmodel.CharactersViewModel

@Composable
fun CharacterDetailScreen(
    charactersViewModel: CharactersViewModel = hiltViewModel(),
    characterId: Int,
    onBack: () -> Unit
) {
    val character = charactersViewModel.selectedCharacter.collectAsState().value

    LaunchedEffect(Unit) {
        charactersViewModel.getInfoCharacter(characterId)
    }


    Scaffold(topBar = {
        TopBarDetailCharacter(
            characterName = character?.name ?: "",
            onBack = {
                onBack()
                charactersViewModel.clearInfoCharacter()
            }
        )
    }) { paddingValues ->
        character?.let {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                CharacterImageSection(image = character.image)
                CharacterAttributeSection(character.status)
                CharacterAttributeSection(character.specie)
                CharacterAttributeSection(character.gender)
                    
                if (character.episodes.isNotEmpty()) {
                    EpisodesSection(episodes = character.episodes)
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}



