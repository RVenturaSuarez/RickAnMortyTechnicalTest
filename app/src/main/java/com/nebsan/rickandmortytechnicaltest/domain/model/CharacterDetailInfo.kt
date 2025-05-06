package com.nebsan.rickandmortytechnicaltest.domain.model

data class CharacterDetailInfo(
    val name: String,
    val status: String,
    val specie: String,
    val gender: String,
    val image: String,
    val episodes: List<Int>
)
