package com.thorhelgen.rickandmortyinfoapp.data.remote.entities

data class Info(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)