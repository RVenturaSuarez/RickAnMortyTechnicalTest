package com.nebsan.rickandmortytechnicaltest.presentation.ui.charactersInfo.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nebsan.rickandmortytechnicaltest.domain.model.CharacterInfo
import com.nebsan.rickandmortytechnicaltest.presentation.ui.core.components.CharacterImageSection

@Composable
fun CharacterCard(characterInfo: CharacterInfo) {
    OutlinedCard(
        Modifier
            .width(300.dp)
            .clickable {}
    ) {
        CharacterImageSection(image = characterInfo.image)
        CharacterNameSection(name = characterInfo.name)
    }
}