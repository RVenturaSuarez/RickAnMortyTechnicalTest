package com.nebsan.rickandmortytechnicaltest.data.paging

import android.content.Context
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nebsan.rickandmortytechnicaltest.R
import com.nebsan.rickandmortytechnicaltest.data.remote.CharactersApi
import com.nebsan.rickandmortytechnicaltest.data.remote.dto.CharacterDto
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class CharacterPagingSource @Inject constructor(
    private val charactersApi: CharactersApi,
    private val characterName: String? = null,
    private val context: Context,
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
            LoadResult.Error(e)
        } catch (e: HttpException) {
            // Check code error
            val messageCharacterNotFound = if (e.code() == 404) {
                context.getString(R.string.error_404)
            } else {
                context.getString(R.string.other_error_http, e.code())
            }
            LoadResult.Error(Exception(messageCharacterNotFound))
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}