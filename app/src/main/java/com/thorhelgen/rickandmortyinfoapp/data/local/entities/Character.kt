package com.thorhelgen.rickandmortyinfoapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Character(
    @PrimaryKey
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val originId: Int,
    val originName: String,
    val locationId: Int,
    val locationName: String,
    val created: String
)
