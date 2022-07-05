package com.thorhelgen.rickandmortyinfoapp.data.local.mappers

import com.thorhelgen.rickandmortyinfoapp.data.local.entities.Episode
import com.thorhelgen.rickandmortyinfoapp.domain.entities.Episode as DomainEpisode

class DomainToDataEpisodeMapper : Mapper<DomainEpisode, Episode> {
    override suspend fun map(srcObj: DomainEpisode): Episode =
        Episode(
            srcObj.id,
            srcObj.name,
            srcObj.air_date,
            srcObj.episode,
            srcObj.created
        )
}