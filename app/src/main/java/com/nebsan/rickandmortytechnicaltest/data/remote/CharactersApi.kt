package com.nebsan.rickandmortytechnicaltest.data.remote

import com.nebsan.rickandmortytechnicaltest.data.remote.dto.CharacterDetailDto
import com.nebsan.rickandmortytechnicaltest.data.remote.dto.CharactersResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface CharactersApi {

    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int = 1): CharactersResponseDto

    @GET("character/{id}")
    suspend fun getCharacterDetailInfo(@Path("id") characterId: Int): CharacterDetailDto

}