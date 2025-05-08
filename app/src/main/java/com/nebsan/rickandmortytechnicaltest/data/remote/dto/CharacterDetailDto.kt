package com.nebsan.rickandmortytechnicaltest.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CharacterDetailDto(
    val name: String,
    val status: String,
    @SerializedName("species") val specie: String,
    val gender: String,
    val image: String,
    @SerializedName("episode") val episodes: List<String>
)
