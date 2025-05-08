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
import com.nebsan.rickandmortytechnicaltest.domain.model.ResponseError
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
    onNavigateToDetail: (Int) -> Unit,
) {

    val characters = charactersViewModel.characters.collectAsLazyPagingItems()
    val characterName = charactersViewModel.characterName.collectAsState().value

    Scaffold(topBar = { TopBarCharacters() }) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            TextFieldCharacters(
                characterName = characterName,
                characterNameChanged = {
                    charactersViewModel.onCharacterNameChanged(it)
                }
            )


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
                            Spacer(modifier = Modifier.weight(1f))

                            val errorMessage = when (val error = e.error) {
                                is ResponseError.NotFound -> stringResource(R.string.character_not_found)
                                is ResponseError.Network -> stringResource(R.string.network_error)
                                is ResponseError.HttpError -> stringResource(
                                    R.string.http_error,
                                    error.code
                                )

                                else -> stringResource(R.string.unknown_error)
                            }

                            Text(
                                text = errorMessage,
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
                        CharactersList(
                            characters = characters,
                            onDetailCharacter = { characterId -> onNavigateToDetail(characterId) },
                            modifier = Modifier.padding(horizontal = 20.dp)
                        )
                    }

                }
            }
        }
    }
}



