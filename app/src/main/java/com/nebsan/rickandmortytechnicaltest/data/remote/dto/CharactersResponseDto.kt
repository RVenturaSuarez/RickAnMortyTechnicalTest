package com.nebsan.rickandmortytechnicaltest.data.remote.dto

data class CharactersResponseDto(
    val info: InfoDto,
    val results: List<CharacterDto>,
)
