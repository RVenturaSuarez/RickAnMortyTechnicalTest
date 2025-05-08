package com.nebsan.rickandmortytechnicaltest.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.nebsan.rickandmortytechnicaltest.presentation.ui.charactersDetail.CharacterDetailScreen
import com.nebsan.rickandmortytechnicaltest.presentation.ui.charactersInfo.CharactersInfoScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview


@FlowPreview
@ExperimentalCoroutinesApi
@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = CharactersInfoScreen
    ) {
        composable<CharactersInfoScreen> {
            CharactersInfoScreen(
                onNavigateToDetail = { characterId ->
                    navController.navigate(CharacterDetailScreen(characterId))
                },
            )
        }

        composable<CharacterDetailScreen> {
            val args = it.toRoute<CharacterDetailScreen>()
            CharacterDetailScreen(
                characterId = args.characterId,
                onBack = { navController.popBackStack() })
        }
    }
}

