package com.nebsan.rickandmortytechnicaltest.presentation.ui.charactersDetail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nebsan.rickandmortytechnicaltest.R

@Composable
fun EpisodesSection(episodes: List<Int>) {
    Column(modifier = Modifier.padding(20.dp)) {
        Text(text = stringResource(R.string.episodes))

        LazyVerticalGrid(
            columns = GridCells.Adaptive(100.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            items(episodes) { numberEpisode ->
                Button(onClick = { }, enabled = false) {
                    Text(text = numberEpisode.toString())
                }
            }
        }
    }
}