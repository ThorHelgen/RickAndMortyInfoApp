package com.thorhelgen.rickandmortyinfoapp.domain.entities

import android.graphics.Bitmap

data class Character(
    val name: String,
    val species: String,
    val status: String,
    val gender: String,
    val image: Bitmap
) {
    override fun equals(other: Any?): Boolean {
        return other is Character &&
            other.name == name &&
            other.species == species &&
            other.status == status &&
            other.gender == gender &&
            other.image == image
    }
}
