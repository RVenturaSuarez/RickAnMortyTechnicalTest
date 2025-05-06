package com.nebsan.rickandmortytechnicaltest.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nebsan.rickandmortytechnicaltest.presentation.ui.charactersDetail.CharacterDetailScreen
import com.nebsan.rickandmortytechnicaltest.presentation.ui.charactersInfo.CharactersInfoScreen
import com.nebsan.rickandmortytechnicaltest.presentation.viewmodel.CharactersViewModel

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    val charactersViewModel: CharactersViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = Destinations.CharactersInfoScreen.route
    ) {
        composable(route = Destinations.CharactersInfoScreen.route) {
            CharactersInfoScreen(
                charactersViewModel = charactersViewModel,
                onDetailCharacter = { characterId ->
                    navController.navigate(
                        Destinations.CharacterDetailScreen.createRoute(characterId)
                    )
                }
            )
        }

        composable(
            route = Destinations.CharacterDetailScreen.route,
            arguments = listOf(
                navArgument(
                    Destinations.CharacterDetailScreen.ARG_CHARACTER_ID,
                    builder = { type = NavType.IntType })
            )
        ) { navBackStackEntry ->
            val characterId =
                navBackStackEntry.arguments?.getInt(Destinations.CharacterDetailScreen.ARG_CHARACTER_ID)
                    ?: -1
            CharacterDetailScreen(
                charactersViewModel = charactersViewModel,
                characterId = characterId,
                onBack = { navController.popBackStack() })
        }
    }
}

