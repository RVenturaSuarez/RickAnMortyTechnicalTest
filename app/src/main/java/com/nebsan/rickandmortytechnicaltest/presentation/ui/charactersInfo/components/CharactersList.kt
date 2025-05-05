package com.nebsan.rickandmortytechnicaltest.presentation.ui.charactersInfo.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nebsan.rickandmortytechnicaltest.domain.model.CharacterInfo

@Composable
fun CharactersList(
    characters: List<CharacterInfo>,
    onDetailCharacter: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.padding(bottom = 20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(characters) { characterInfo ->
            CharacterCard(
                characterInfo = characterInfo,
                onDetailCharacter = { characterId -> onDetailCharacter(characterId) })
        }
    }
}