package com.thorhelgen.rickandmortyinfoapp

import android.net.Uri
import com.thorhelgen.rickandmortyinfoapp.data.remote.mappers.CharacterMapper
import com.thorhelgen.rickandmortyinfoapp.data.remote.entities.Character
import com.thorhelgen.rickandmortyinfoapp.data.remote.entities.CharacterLocation
import com.thorhelgen.rickandmortyinfoapp.data.remote.entities.CharacterOrigin
import com.thorhelgen.rickandmortyinfoapp.domain.entities.Character as DomainCharacter
import io.mockk.every
import io.mockk.mockkStatic
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TestRemoteCharacterMapper {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        mockkStatic(Uri::class)
        every {
            Uri.parse("https://rickandmortyapi.com/api/location/1").lastPathSegment
        } returns "1"
        every {
            Uri.parse("https://rickandmortyapi.com/api/location/20").lastPathSegment
        } returns "20"
    }

    @Test
    @ExperimentalMultiplatform
    fun map_test() = runTest {
        val mapper = CharacterMapper()
        val testCharacter = Character(
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
        val testDomainCharacter = DomainCharacter(
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

        val result = mapper.map(testCharacter)
        assertEquals(
            result,
            testDomainCharacter
        )
    }
}