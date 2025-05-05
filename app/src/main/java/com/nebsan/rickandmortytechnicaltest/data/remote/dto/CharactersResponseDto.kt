package com.nebsan.rickandmortytechnicaltest.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CharactersResponseDto(
    @SerializedName("info") val info: InfoDto,
    @SerializedName("results") val results: List<CharacterDto>
)
