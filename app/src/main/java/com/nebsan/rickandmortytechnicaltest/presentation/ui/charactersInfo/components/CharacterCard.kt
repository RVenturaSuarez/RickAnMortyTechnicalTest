package com.nebsan.rickandmortytechnicaltest.presentation.ui.charactersInfo.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nebsan.rickandmortytechnicaltest.domain.model.CharacterInfo
import com.nebsan.rickandmortytechnicaltest.presentation.ui.core.components.CharacterImageSection

@Composable
fun CharacterCard(characterInfo: CharacterInfo, onDetailCharacter: (Int) -> Unit) {
    OutlinedCard(
        Modifier
            .width(300.dp)
            .clickable { onDetailCharacter(characterInfo.id) }
    ) {
        CharacterImageSection(image = characterInfo.image)
        CharacterNameSection(name = characterInfo.name)
    }
}

@Composable
@Preview
fun CharacterCard() {
    val characterInfo = CharacterInfo(
        id = 1,
        name = "Rick Sanchez",
        image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
    )

    OutlinedCard(
        Modifier
            .width(300.dp)
            .clickable { }
    ) {
        CharacterImageSection(image = characterInfo.image)
        CharacterNameSection(name = characterInfo.name)
    }
}