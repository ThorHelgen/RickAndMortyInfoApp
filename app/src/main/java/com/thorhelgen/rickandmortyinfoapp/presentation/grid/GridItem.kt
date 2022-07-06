package com.thorhelgen.rickandmortyinfoapp.presentation.grid

import android.graphics.Bitmap

data class GridItem(
    val id: Int,
    val name: String,
    val image: Bitmap?,
    val firstAdditionalLine: String = "",
    val secondAdditionalLine: String = "",
    val thirdAdditionalLine: String = ""
) {
    override fun equals(other: Any?): Boolean =
        other is GridItem &&
        other.name == name &&
        other.image == image &&
        other.firstAdditionalLine == firstAdditionalLine &&
        other.secondAdditionalLine == secondAdditionalLine &&
        other.thirdAdditionalLine == thirdAdditionalLine
}
