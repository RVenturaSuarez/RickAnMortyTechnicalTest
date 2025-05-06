package com.nebsan.rickandmortytechnicaltest.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CharacterDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: String
)
