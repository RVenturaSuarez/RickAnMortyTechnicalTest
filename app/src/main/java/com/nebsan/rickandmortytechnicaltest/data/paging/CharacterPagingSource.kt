package com.nebsan.rickandmortytechnicaltest.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nebsan.rickandmortytechnicaltest.data.remote.CharactersApi
import com.nebsan.rickandmortytechnicaltest.data.remote.dto.CharacterDto
import com.nebsan.rickandmortytechnicaltest.domain.model.ResponseError
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class CharacterPagingSource @Inject constructor(
    private val charactersApi: CharactersApi,
    private val characterName: String? = null,
) :
    PagingSource<Int, CharacterDto>() {

    override fun getRefreshKey(state: PagingState<Int, CharacterDto>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterDto> {
        return try {
            val page = params.key ?: 1
            val responseDto = charactersApi.getCharacters(page, characterName)
            val characters = responseDto.results

            val nextKey = if (responseDto.info.next != null) page + 1 else null
            val prevKey = if (responseDto.info.prev != null) page - 1 else null

            LoadResult.Page(data = characters, prevKey = prevKey, nextKey = nextKey)
        } catch (e: IOException) {
            LoadResult.Error(ResponseError.Network)
        } catch (e: HttpException) {
            val error = if (e.code() == 404) {
                ResponseError.NotFound
            } else {
                ResponseError.HttpError(e.code())
            }
            LoadResult.Error(error)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}