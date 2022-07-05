package com.thorhelgen.rickandmortyinfoapp.data.local.mappers

import com.thorhelgen.rickandmortyinfoapp.data.local.entities.Location
import com.thorhelgen.rickandmortyinfoapp.domain.entities.Location as DomainLocation

class DomainToDataLocationMapper : Mapper<DomainLocation, Location> {
    override suspend fun map(srcObj: DomainLocation): Location =
        Location(
            srcObj.id,
            srcObj.name,
            srcObj.type,
            srcObj.dimension,
            srcObj.created
        )
}