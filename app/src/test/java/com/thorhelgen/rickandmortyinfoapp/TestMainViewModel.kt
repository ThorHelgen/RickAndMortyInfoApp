package com.thorhelgen.rickandmortyinfoapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.thorhelgen.rickandmortyinfoapp.domain.entities.Character
import com.thorhelgen.rickandmortyinfoapp.domain.entities.Episode
import com.thorhelgen.rickandmortyinfoapp.domain.entities.Location
import com.thorhelgen.rickandmortyinfoapp.domain.usecases.UseCases
import com.thorhelgen.rickandmortyinfoapp.presentation.grid.GridItem
import com.thorhelgen.rickandmortyinfoapp.presentation.mainscreen.MainViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class TestMainViewModel {
    private val useCases: UseCases = mockk()
    private lateinit var viewModel: MainViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        coEvery {
            useCases.getCharactersPage(0)
        } answers {
            listOf(Character(
                1,
                "Rick",
                "Alive",
                "Human",
                "",
                "Male",
                1,
                "Earth",
                20,
                "Earth",
                null,
                "2017-11-04T18:48:46.250Z"
            ))
        }
        coEvery {
            useCases.getLocationsPage(0)
        } answers {
            listOf(Location(
                1,
                "Earth",
                "Planet",
                "Dimension C-137",
                "2017-11-10T12:42:04.162Z"
            ))
        }
        coEvery {
            useCases.getEpisodesPage(0)
        } answers {
            listOf(
                Episode(
                1,
                "Pilot",
                "December 2, 2013",
                "S01E01",
                "2017-11-10T12:56:33.798Z"
            ))
        }
        viewModel = MainViewModel(useCases)
//        every { observer.onChanged(any()) } answers {  }
    }

    @Test
    fun loadCharactersList_test() = runTest {
        viewModel.loadCharactersList(0)
        coVerify { useCases.getCharactersPage(0) }
        assertEquals(
            viewModel.itemsList.value,
            listOf(GridItem(
                1,
                "Rick",
                null,
                "Human",
                "Earth",
                "Earth"
            )))
    }

    @Test
    fun loadLocationsList_test() = runTest {
        viewModel.loadLocationsList(0)
        coVerify { useCases.getLocationsPage(0) }
        assertEquals(
            viewModel.itemsList.value,
            listOf(GridItem(
                1,
                "Earth",
                null,
                "Planet",
                "Dimension C-137",
            )))
    }

    @Test
    fun loadEpisodesList_test() = runTest {
        viewModel.loadEpisodesList(0)
        coVerify { useCases.getEpisodesPage(0) }
        assertEquals(
            viewModel.itemsList.value,
            listOf(GridItem(
                1,
                "Pilot",
                null,
                "S01E01",
                "December 2, 2013",
            )))
    }
}