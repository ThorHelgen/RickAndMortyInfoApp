package com.thorhelgen.rickandmortyinfoapp.domain.entities

import android.graphics.Bitmap

data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val originId: Int,
    val locationId: Int,
    val image: Bitmap?,
    val created: String
)
