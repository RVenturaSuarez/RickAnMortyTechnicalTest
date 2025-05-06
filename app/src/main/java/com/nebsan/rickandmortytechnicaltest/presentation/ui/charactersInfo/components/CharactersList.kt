package com.nebsan.rickandmortytechnicaltest.presentation.ui.charactersInfo.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.nebsan.rickandmortytechnicaltest.R
import com.nebsan.rickandmortytechnicaltest.domain.model.CharacterInfo

@Composable
fun CharactersList(
    characters: LazyPagingItems<CharacterInfo>,
    onDetailCharacter: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.padding(bottom = 20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(characters.itemCount) { index ->
            characters[index]?.let { characterInfo ->
                CharacterCard(
                    characterInfo = characterInfo,
                    onDetailCharacter = { characterId -> onDetailCharacter(characterId) })
            }
        }

        item {
            when (characters.loadState.append) {
                is LoadState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                }
                is LoadState.Error -> {
                    val e = characters.loadState.append as LoadState.Error
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = stringResource(id = R.string.message_error, e.error.message ?: "Unknown"),)
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = { characters.retry() }) {
                            Text(stringResource(id = R.string.btn_retry))
                        }
                    }
                }
                else -> {}
            }
        }

    }
}