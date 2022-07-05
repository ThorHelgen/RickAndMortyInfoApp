package com.thorhelgen.rickandmortyinfoapp.data.remote.mappers

interface Mapper <TSrc, TDst> {
    suspend fun map(srcObj: TSrc): TDst
}