package com.thorhelgen.rickandmortyinfoapp

import com.thorhelgen.rickandmortyinfoapp.data.remote.entities.Location
import com.thorhelgen.rickandmortyinfoapp.data.remote.mappers.LocationMapper
import com.thorhelgen.rickandmortyinfoapp.domain.entities.Location as DomainLocation
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class TestRemoteLocationMapper {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Test
    @ExperimentalMultiplatform
    fun map_test() = runTest {
        val mapper = LocationMapper()
        val testLocation = Location(
            1,
            "Earth",
            "Planet",
            "Dimension C-137",
            listOf("https://rickandmortyapi.com/api/character/1"),
            "https://rickandmortyapi.com/api/location/1",
            "2017-11-10T12:42:04.162Z"
        )
        val testDomainLocation = DomainLocation(
            1,
            "Earth",
            "Planet",
            "Dimension C-137",
            "2017-11-10T12:42:04.162Z"
        )

        val result = mapper.map(testLocation)
        assertEquals(
            result,
            testDomainLocation
        )
    }
}