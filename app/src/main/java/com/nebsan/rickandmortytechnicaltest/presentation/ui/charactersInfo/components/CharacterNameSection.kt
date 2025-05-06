package com.nebsan.rickandmortytechnicaltest.presentation.ui.charactersInfo.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CharacterNameSection(name: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(Color.Black)
            .fillMaxWidth()
    ) {
        Text(text = name, color = Color.White, modifier = Modifier.padding(vertical = 5.dp))
    }
}