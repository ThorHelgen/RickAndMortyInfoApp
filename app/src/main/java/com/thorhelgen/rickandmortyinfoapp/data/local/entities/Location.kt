package com.thorhelgen.rickandmortyinfoapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Location(
    @PrimaryKey
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val created: String
)
