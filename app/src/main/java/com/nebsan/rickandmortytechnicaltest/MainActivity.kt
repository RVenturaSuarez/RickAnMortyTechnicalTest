package com.nebsan.rickandmortytechnicaltest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.nebsan.rickandmortytechnicaltest.presentation.navigation.AppNavigation
import com.nebsan.rickandmortytechnicaltest.ui.theme.RickAndMortyTechnicalTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            RickAndMortyTechnicalTestTheme(darkTheme = true) {
                AppNavigation()
            }
        }
    }
}
