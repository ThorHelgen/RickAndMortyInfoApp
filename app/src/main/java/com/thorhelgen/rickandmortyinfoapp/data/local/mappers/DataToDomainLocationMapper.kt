package com.thorhelgen.rickandmortyinfoapp.data.local.mappers

import com.thorhelgen.rickandmortyinfoapp.data.local.entities.Location
import com.thorhelgen.rickandmortyinfoapp.domain.entities.Location as DomainLocation

class DataToDomainLocationMapper : Mapper<Location, DomainLocation> {
    override suspend fun map(srcObj: Location): DomainLocation =
        DomainLocation(
            srcObj.id,
            srcObj.name,
            srcObj.type,
            srcObj.dimension,
            srcObj.created
        )
}