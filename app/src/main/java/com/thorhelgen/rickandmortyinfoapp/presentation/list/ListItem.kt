package com.thorhelgen.rickandmortyinfoapp.presentation.list

data class ListItem (
    val id: Int,
    val name: String,
    val firstAdditionalLine: String,
    val secondAdditionalLine: String
) {
    override fun equals(other: Any?): Boolean =
        other is ListItem &&
        other.name == name &&
        other.firstAdditionalLine == firstAdditionalLine &&
        other.secondAdditionalLine == secondAdditionalLine
}