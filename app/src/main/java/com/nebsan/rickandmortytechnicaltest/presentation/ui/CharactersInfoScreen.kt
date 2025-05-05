package com.nebsan.rickandmortytechnicaltest.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nebsan.rickandmortytechnicaltest.presentation.viewmodel.CharactersViewModel

@Composable
fun CharactersInfoScreen(charactersViewModel: CharactersViewModel = hiltViewModel()) {

    val characters = charactersViewModel.characters.collectAsState().value

    Scaffold { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(characters) { characterInfo ->
                Box(modifier = Modifier.size(200.dp)) {
                    Text(text = characterInfo.name)
                }
            }
        }
    }


}