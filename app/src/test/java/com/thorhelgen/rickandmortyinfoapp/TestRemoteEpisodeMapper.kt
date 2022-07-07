package com.thorhelgen.rickandmortyinfoapp

import com.thorhelgen.rickandmortyinfoapp.data.remote.entities.Episode
import com.thorhelgen.rickandmortyinfoapp.domain.entities.Episode as DomainEpisode
import com.thorhelgen.rickandmortyinfoapp.data.remote.mappers.EpisodeMapper
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class TestRemoteEpisodeMapper {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Test
    @ExperimentalMultiplatform
    fun map_test() = runTest {
        val mapper = EpisodeMapper()
        val testEpisode = Episode(
            1,
            "Pilot",
            "December 2, 2013",
            "S01E01",
            listOf("https://rickandmortyapi.com/api/character/1"),
            "https://rickandmortyapi.com/api/episode/1",
            "2017-11-10T12:56:33.798Z"
        )
        val testDomainEpisode = DomainEpisode(
            1,
            "Pilot",
            "December 2, 2013",
            "S01E01",
            "2017-11-10T12:56:33.798Z"
        )

        val result = mapper.map(testEpisode)
        assertEquals(
            result,
            testDomainEpisode
        )
    }
}