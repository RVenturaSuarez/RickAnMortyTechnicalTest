package com.nebsan.rickandmortytechnicaltest.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CharacterDetailDto(
    @SerializedName("name") val name: String,
    @SerializedName("status") val status: String,
    @SerializedName("species") val specie: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("image") val image: String,
    @SerializedName("episode") val episodes: List<String>
)
