package com.thorhelgen.rickandmortyinfoapp.data.local.mappers

interface Mapper <TSrc, TDst> {
    suspend fun map(srcObj: TSrc): TDst
}