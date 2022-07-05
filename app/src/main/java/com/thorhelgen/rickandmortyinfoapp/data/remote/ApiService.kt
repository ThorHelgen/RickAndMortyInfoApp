package com.thorhelgen.rickandmortyinfoapp.data.remote

import com.thorhelgen.rickandmortyinfoapp.data.remote.entities.*
import com.thorhelgen.rickandmortyinfoapp.data.remote.entities.Character
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

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
        @Query("status") status: String = "",
        @Query("species") species: String = "",
        @Query("type") type: String = "",
        @Query("gender") gender: String = ""
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

    @GET
    suspend fun getImage(@Url url: String): Call<ResponseBody>
}