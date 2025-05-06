package com.nebsan.rickandmortytechnicaltest.presentation.navigation

sealed class Destinations(val route: String) {
    data object CharactersInfoScreen : Destinations("CharactersInfoScreen")
    data object CharacterDetailScreen : Destinations("CharacterDetailScreen/{characterId}") {
        const val ARG_CHARACTER_ID = "characterId"
        fun createRoute(characterId: Int) = "CharacterDetailScreen/$characterId"
    }

}