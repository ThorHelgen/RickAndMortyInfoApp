package com.thorhelgen.rickandmortyinfoapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Episode(
    @PrimaryKey
    val id: Int,
    val name: String,
    val air_date: String,
    val episode: String,
    val created: String
)
