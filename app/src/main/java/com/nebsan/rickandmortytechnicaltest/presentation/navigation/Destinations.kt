package com.nebsan.rickandmortytechnicaltest.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
object CharactersInfoScreen

@Serializable
data class CharacterDetailScreen(val characterId: Int)