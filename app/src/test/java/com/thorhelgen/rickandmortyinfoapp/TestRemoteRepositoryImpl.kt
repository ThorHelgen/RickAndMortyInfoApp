package com.thorhelgen.rickandmortyinfoapp

import android.net.Uri
import com.thorhelgen.rickandmortyinfoapp.data.remote.ApiService
import com.thorhelgen.rickandmortyinfoapp.data.remote.RemoteRepositoryImpl
import com.thorhelgen.rickandmortyinfoapp.data.remote.entities.*
import com.thorhelgen.rickandmortyinfoapp.domain.entities.Character as DomainCharacter
import com.thorhelgen.rickandmortyinfoapp.domain.entities.Location as DomainLocation
import com.thorhelgen.rickandmortyinfoapp.domain.entities.Episode as DomainEpisode
import com.thorhelgen.rickandmortyinfoapp.data.remote.mappers.CharacterMapper
import com.thorhelgen.rickandmortyinfoapp.data.remote.mappers.EpisodeMapper
import com.thorhelgen.rickandmortyinfoapp.data.remote.mappers.LocationMapper
import com.thorhelgen.rickandmortyinfoapp.domain.entities.CharacterDetails
import com.thorhelgen.rickandmortyinfoapp.domain.entities.EpisodeDetails
import com.thorhelgen.rickandmortyinfoapp.domain.entities.LocationDetails
import com.thorhelgen.rickandmortyinfoapp.domain.usecases.RemoteRepository
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TestRemoteRepositoryImpl {
    private val apiService: ApiService = mockk()
    private lateinit var remoteRepository: RemoteRepository
    private lateinit var testCharacter: Character
    private lateinit var testDomainCharacter: DomainCharacter
    private lateinit var testLocation: Location
    private lateinit var testDomainLocation: DomainLocation
    private lateinit var testEpisode: Episode
    private lateinit var testDomainEpisode: DomainEpisode

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        val defaultInfo = Info(
                1,
                1,
                null,
                null
            )

        testCharacter = Character(
            1,
            "Rick",
            "Alive",
            "Human",
            "",
            "Male",
            CharacterOrigin(
                "Earth",
                "https://rickandmortyapi.com/api/location/1"
            ),
            CharacterLocation(
                "Earth",
                "https://rickandmortyapi.com/api/location/20"
            ),
            "",
            listOf("https://rickandmortyapi.com/api/episode/1"),
            "https://rickandmortyapi.com/api/character/1",
            "2017-11-04T18:48:46.250Z"
        )
        testDomainCharacter = DomainCharacter(
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
        )

        testLocation = Location(
            1,
            "Earth",
            "Planet",
            "Dimension C-137",
            listOf("https://rickandmortyapi.com/api/character/1"),
            "https://rickandmortyapi.com/api/location/1",
            "2017-11-10T12:42:04.162Z"
        )
        testDomainLocation = DomainLocation(
            1,
            "Earth",
            "Planet",
            "Dimension C-137",
            "2017-11-10T12:42:04.162Z"
        )

        testEpisode = Episode(
            1,
            "Pilot",
            "December 2, 2013",
            "S01E01",
            listOf("https://rickandmortyapi.com/api/character/1"),
            "https://rickandmortyapi.com/api/episode/1",
            "2017-11-10T12:56:33.798Z"
        )
        testDomainEpisode = DomainEpisode(
            1,
            "Pilot",
            "December 2, 2013",
            "S01E01",
            "2017-11-10T12:56:33.798Z"
        )

        coEvery {
            apiService.getCharactersPage(0)
        } returns Characters(
                defaultInfo,
                listOf(testCharacter)
        )

        coEvery {
            apiService.getLocationsPage(0)
        } returns Locations(
                defaultInfo,
                listOf(testLocation)
        )

        coEvery {
            apiService.getEpisodesPage(0)
        } returns Episodes(
            defaultInfo,
            listOf(testEpisode)
        )

        coEvery {
            apiService.getCharacterById(1)
        } returns testCharacter

        coEvery {
            apiService.getLocationById(1)
        } returns testLocation

        coEvery {
            apiService.getEpisodeById(1)
        } returns testEpisode

        coEvery {
            apiService.getCharactersPage(
                0,
                "Rick",
                "Alive",
                "Human",
                "",
                "Male"
            )
        } returns Characters(
            defaultInfo,
            listOf(testCharacter)
        )

        coEvery {
            apiService.getLocationsPage(
                0,
                "Earth",
                "Planet",
                "Dimension C-137",
            )
        } returns Locations(
            defaultInfo,
            listOf(testLocation)
        )

        coEvery {
            apiService.getEpisodesPage(
                0,
                "Pilot",
                "S01E01",
            )
        } returns Episodes(
            defaultInfo,
            listOf(testEpisode)
        )

        mockkStatic(Uri::class)
        every {
            Uri.parse("https://rickandmortyapi.com/api/episode/1").lastPathSegment
        } returns "1"
        every {
            Uri.parse("https://rickandmortyapi.com/api/character/1").lastPathSegment
        } returns "1"

        mockkConstructor(CharacterMapper::class)
        coEvery {
            anyConstructed<CharacterMapper>().map(testCharacter)
        } returns testDomainCharacter

        mockkConstructor(LocationMapper::class)
        coEvery {
            anyConstructed<LocationMapper>().map(testLocation)
        } returns testDomainLocation

        mockkConstructor(EpisodeMapper::class)
        coEvery {
            anyConstructed<EpisodeMapper>().map(testEpisode)
        } returns testDomainEpisode

        remoteRepository = RemoteRepositoryImpl(apiService)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun getCharactersPage_test() = runTest {
        val characters: List<DomainCharacter> = remoteRepository.getCharactersPage(0)
        coVerify {
            anyConstructed<CharacterMapper>().map(testCharacter)
        }
        assertEquals(
            characters,
            listOf(testDomainCharacter)
        )
    }

    @Test
    @ExperimentalCoroutinesApi
    fun getLocationsPage_test() = runTest {
        val locations: List<DomainLocation> = remoteRepository.getLocationsPage(0)
        coVerify {
            anyConstructed<LocationMapper>().map(testLocation)
        }
        assertEquals(
            locations,
            listOf(testDomainLocation)
        )
    }

    @Test
    @ExperimentalCoroutinesApi
    fun getEpisodesPage_test() = runTest {
        val episodes: List<DomainEpisode> = remoteRepository.getEpisodesPage(0)
        coVerify {
            anyConstructed<EpisodeMapper>().map(testEpisode)
        }
        assertEquals(
            episodes,
            listOf(testDomainEpisode)
        )
    }

    @Test
    @ExperimentalCoroutinesApi
    fun getCharacterDetails_test() = runTest {
        val characterDetails: CharacterDetails? = remoteRepository.getCharacterDetails(1)
        coVerify {
            anyConstructed<EpisodeMapper>().map(testEpisode)
            anyConstructed<CharacterMapper>().map(testCharacter)
        }
        assertEquals(
            characterDetails,
            CharacterDetails(
                testDomainCharacter,
                listOf(testDomainEpisode)
            )
        )
    }

    @Test
    @ExperimentalCoroutinesApi
    fun getLocationDetails_test() = runTest {
        val locationDetails: LocationDetails? = remoteRepository.getLocationDetails(1)
        coVerify {
            anyConstructed<CharacterMapper>().map(testCharacter)
            anyConstructed<LocationMapper>().map(testLocation)
        }
        assertEquals(
            locationDetails,
            LocationDetails(
                testDomainLocation,
                listOf(testDomainCharacter)
            )
        )
    }

    @Test
    @ExperimentalCoroutinesApi
    fun getEpisodeDetails_test() = runTest {
        val episodeDetails: EpisodeDetails? = remoteRepository.getEpisodeDetails(1)
        coVerify {
            anyConstructed<CharacterMapper>().map(testCharacter)
            anyConstructed<EpisodeMapper>().map(testEpisode)
        }
        assertEquals(
            episodeDetails,
            EpisodeDetails(
                testDomainEpisode,
                listOf(testDomainCharacter)
            )
        )
    }

    @Test
    @ExperimentalCoroutinesApi
    fun filterCharacters_test() = runTest {
        val characters: List<DomainCharacter> = remoteRepository.filterCharacters(
            0,
            "Rick",
            "Alive",
            "Human",
            "",
            "Male"
        )
        coVerify {
            anyConstructed<CharacterMapper>().map(testCharacter)
        }
        assertEquals(
            characters,
            listOf(testDomainCharacter)
        )
    }

    @Test
    @ExperimentalCoroutinesApi
    fun filterLocations_test() = runTest {
        val locations: List<DomainLocation> = remoteRepository.filterLocations(
            0,
            "Earth",
            "Planet",
            "Dimension C-137"
        )
        coVerify {
            anyConstructed<LocationMapper>().map(testLocation)
        }
        assertEquals(
            locations,
            listOf(testDomainLocation)
        )
    }

    @Test
    @ExperimentalCoroutinesApi
    fun filterEpisodes_test() = runTest {
        val episodes: List<DomainEpisode> = remoteRepository.filterEpisodes(
            0,
            "Pilot",
            "S01E01"
        )
        coVerify {
            anyConstructed<EpisodeMapper>().map(testEpisode)
        }
        assertEquals(
            episodes,
            listOf(testDomainEpisode)
        )
    }
}