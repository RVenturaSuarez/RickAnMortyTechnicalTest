package com.nebsan.rickandmortytechnicaltest.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.nebsan.rickandmortytechnicaltest.common.DispatcherProvider
import com.nebsan.rickandmortytechnicaltest.data.paging.CharacterPagingSource
import com.nebsan.rickandmortytechnicaltest.data.remote.CharactersApi
import com.nebsan.rickandmortytechnicaltest.data.remote.dto.CharacterDetailDto
import com.nebsan.rickandmortytechnicaltest.data.remote.dto.CharacterDto
import com.nebsan.rickandmortytechnicaltest.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val charactersApi: CharactersApi,
    private val dispatcherProvider: DispatcherProvider
) :
    CharactersRepository {

    companion object {
        const val PAGE_SIZE = 20
        const val PREFETCH_ITEMS = 3
    }


    override fun getCharacters(characterName: String?): Flow<PagingData<CharacterDto>> {
        return Pager(config = PagingConfig(pageSize = PAGE_SIZE, prefetchDistance = PREFETCH_ITEMS),
            pagingSourceFactory = {
                CharacterPagingSource(charactersApi, characterName)
            }).flow
    }

    override suspend fun getCharacterDetailInfo(characterId: Int): Result<CharacterDetailDto> {
        return runCatching {
            withContext(dispatcherProvider.io) {
                charactersApi.getCharacterDetailInfo(characterId)
            }
        }
    }
}