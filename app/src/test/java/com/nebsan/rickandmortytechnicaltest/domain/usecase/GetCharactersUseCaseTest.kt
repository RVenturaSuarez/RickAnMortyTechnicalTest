package com.nebsan.rickandmortytechnicaltest.domain.usecase

import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import com.nebsan.rickandmortytechnicaltest.data.remote.dto.CharacterDetailDto
import com.nebsan.rickandmortytechnicaltest.data.remote.dto.CharacterDto
import com.nebsan.rickandmortytechnicaltest.domain.model.CharacterInfo
import com.nebsan.rickandmortytechnicaltest.domain.repository.CharactersRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetCharactersUseCaseTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var getCharactersUseCase: GetCharactersUseCase


    class FakeCharactersRepository : CharactersRepository {
        override fun getCharacters(name: String?): Flow<PagingData<CharacterDto>> {
            val dtoList = listOf(
                CharacterDto(1, "Rick Sanchez", "https://rickandmortyapi.com/api/character/avatar/1.jpeg"),
                CharacterDto(2, "Morty Smith", "https://rickandmortyapi.com/api/character/avatar/2.jpeg"),
            )
            val pagingData = PagingData.from(dtoList)
            return flowOf(pagingData)
        }

        override suspend fun getCharacterDetailInfo(characterId: Int): Result<CharacterDetailDto> {
            return Result.failure(Exception("Exception"))
        }
    }

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        getCharactersUseCase = GetCharactersUseCase(FakeCharactersRepository())
    }




    @Test
    fun `getCharacters returns correct data`() = runTest {
        val differ = AsyncPagingDataDiffer(
            diffCallback = CharacterInfoDiffCallback(),
            updateCallback = NoopListCallback(),
            workerDispatcher = testDispatcher
        )

        val flow = getCharactersUseCase()
        val pagingData = flow.first()
        differ.submitData(pagingData)

        advanceUntilIdle()

        val items = differ.snapshot().items
        assertEquals(2, items.size)
        assertEquals("Rick Sanchez", items[0].name)
        assertEquals("Morty Smith", items[1].name)
    }


    // Necessary for differ
    class CharacterInfoDiffCallback : DiffUtil.ItemCallback<CharacterInfo>() {
        override fun areItemsTheSame(oldItem: CharacterInfo, newItem: CharacterInfo) = oldItem.name == newItem.name
        override fun areContentsTheSame(oldItem: CharacterInfo, newItem: CharacterInfo) = oldItem == newItem
    }

    class NoopListCallback : ListUpdateCallback {
        override fun onInserted(position: Int, count: Int) = Unit
        override fun onRemoved(position: Int, count: Int) = Unit
        override fun onMoved(fromPosition: Int, toPosition: Int) = Unit
        override fun onChanged(position: Int, count: Int, payload: Any?) = Unit
    }
}