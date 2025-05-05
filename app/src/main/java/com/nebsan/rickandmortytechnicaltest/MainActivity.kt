package com.nebsan.rickandmortytechnicaltest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.nebsan.rickandmortytechnicaltest.presentation.ui.charactersDetail.CharactersDetailScreen
import com.nebsan.rickandmortytechnicaltest.presentation.ui.charactersInfo.CharactersInfoScreen
import com.nebsan.rickandmortytechnicaltest.ui.theme.RickAndMortyTechnicalTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RickAndMortyTechnicalTestTheme(darkTheme = true) {
                //CharactersInfoScreen()
                CharactersDetailScreen()
            }
        }
    }
}
