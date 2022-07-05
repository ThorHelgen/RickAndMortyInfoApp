package com.thorhelgen.rickandmortyinfoapp.data.remote.mappers

import com.thorhelgen.rickandmortyinfoapp.data.remote.entities.Location
import com.thorhelgen.rickandmortyinfoapp.domain.entities.Location as DomainLocation

class LocationMapper : Mapper<Location, DomainLocation> {
    override suspend fun map(srcObj: Location): DomainLocation =
        DomainLocation(
            srcObj.id,
            srcObj.name,
            srcObj.type,
            srcObj.dimension,
            srcObj.created
        )
}