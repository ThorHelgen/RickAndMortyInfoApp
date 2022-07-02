package com.thorhelgen.rickandmortyinfoapp.domain.entities

data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val originId: Int,
    val locationId: Int,
    val imageName: String,
    val created: String
)
