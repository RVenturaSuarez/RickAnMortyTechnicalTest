package com.nebsan.rickandmortytechnicaltest.presentation.ui.charactersInfo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.nebsan.rickandmortytechnicaltest.R
import com.nebsan.rickandmortytechnicaltest.presentation.ui.charactersInfo.components.CharactersList
import com.nebsan.rickandmortytechnicaltest.presentation.ui.charactersInfo.components.TextFieldCharacters
import com.nebsan.rickandmortytechnicaltest.presentation.ui.charactersInfo.components.TopBarCharacters
import com.nebsan.rickandmortytechnicaltest.presentation.viewmodel.CharactersViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@OptIn(FlowPreview::class)
@ExperimentalCoroutinesApi
@Composable
fun CharactersInfoScreen(
    charactersViewModel: CharactersViewModel = hiltViewModel(),
    onDetailCharacter: (Int) -> Unit,
) {

    val characters = charactersViewModel.characters.collectAsLazyPagingItems()
    var characterName = charactersViewModel.characterName.collectAsState().value

    Scaffold(topBar = { TopBarCharacters() }) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            when (characters.loadState.refresh) {
                is LoadState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                is LoadState.Error -> {
                    val e = characters.loadState.refresh as LoadState.Error
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(20.dp)
                        ) {
                            TextFieldCharacters(
                                characterName = characterName,
                                characterNameChanged = {
                                    charactersViewModel.onCharacterNameChanged(it)
                                }
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = stringResource(
                                    id = R.string.message_error,
                                    e.error.message ?: "Unknown"
                                ),
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Button(onClick = { characters.retry() }) {
                                Text(stringResource(id = R.string.btn_retry))
                            }
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }

                else -> {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        TextFieldCharacters(
                            characterName = characterName,
                            characterNameChanged = {
                                charactersViewModel.onCharacterNameChanged(it)
                            }
                        )
                        CharactersList(
                            characters = characters,
                            onDetailCharacter = onDetailCharacter,
                            modifier = Modifier.padding(horizontal = 20.dp)
                        )
                    }

                }
            }
        }
    }
}



