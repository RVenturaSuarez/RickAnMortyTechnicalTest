package com.nebsan.rickandmortytechnicaltest.presentation.ui.charactersInfo.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nebsan.rickandmortytechnicaltest.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarCharacters() {
    CenterAlignedTopAppBar(
        title = { Text(text = stringResource(id = R.string.title_topBarCharacters)) },
        modifier = Modifier.padding(horizontal = 20.dp)
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun TopBarCharactersPreview() {
    CenterAlignedTopAppBar(
        title = { Text(text = stringResource(id = R.string.title_topBarCharacters)) },
        modifier = Modifier.padding(horizontal = 20.dp)
    )
}