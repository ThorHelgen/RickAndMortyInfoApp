package com.thorhelgen.rickandmortyinfoapp.data.remote

import com.thorhelgen.rickandmortyinfoapp.data.remote.entities.*
import com.thorhelgen.rickandmortyinfoapp.data.remote.entities.Character
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("character")
    suspend fun getCharactersPage(
        @Query("page") pageNumber: Int,
        @Query("name") name: String = "",
        @Query("status") status: String = "",
        @Query("species") species: String = "",
        @Query("type") type: String = "",
        @Query("gender") gender: String = ""
    ): Characters
    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): Character

    @GET("location")
    suspend fun getLocationsPage(
        @Query("page") pageNumber: Int,
        @Query("name") name: String = "",
        @Query("type") type: String = "",
        @Query("dimension") dimension: String = ""
    ): Locations
    @GET("location/{id}")
    suspend fun getLocationById(@Path("id") id: Int): Location

    @GET("episode")
    suspend fun getEpisodesPage(
        @Query("page") pageNumber: Int,
        @Query("name") name: String = "",
        @Query("episode") episode: String = ""
    ): Episodes
    @GET("episode/{id}")
    suspend fun getEpisodeById(@Path("id") id: Int): Episode
}