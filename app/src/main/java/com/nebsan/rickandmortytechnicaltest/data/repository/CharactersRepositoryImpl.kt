package com.nebsan.rickandmortytechnicaltest.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.nebsan.rickandmortytechnicaltest.data.paging.CharacterPagingSource
import com.nebsan.rickandmortytechnicaltest.data.remote.CharactersApi
import com.nebsan.rickandmortytechnicaltest.data.remote.dto.CharacterDetailDto
import com.nebsan.rickandmortytechnicaltest.data.remote.dto.CharacterDto
import com.nebsan.rickandmortytechnicaltest.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(private val charactersApi: CharactersApi) :
    CharactersRepository {

    companion object {
        const val PAGE_SIZE = 10
        const val PREFETCH_ITEMS = 3
    }


    override fun getCharacters(): Flow<PagingData<CharacterDto>> {
        return Pager(config = PagingConfig(pageSize = PAGE_SIZE, prefetchDistance = PREFETCH_ITEMS),
            pagingSourceFactory = {
                CharacterPagingSource(charactersApi)
            }).flow
    }

    override suspend fun getCharacterDetailInfo(characterId: Int): Result<CharacterDetailDto> {
        return try {
            val character = charactersApi.getCharacterDetailInfo(characterId)
            Result.success(character)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}