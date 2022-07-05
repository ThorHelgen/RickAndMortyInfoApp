package com.thorhelgen.rickandmortyinfoapp.data.local.mappers

import com.thorhelgen.rickandmortyinfoapp.data.local.entities.Episode
import com.thorhelgen.rickandmortyinfoapp.domain.entities.Episode as DomainEpisode

class DataToDomainEpisodeMapper : Mapper<Episode, DomainEpisode> {
    override suspend fun map(srcObj: Episode): DomainEpisode =
        DomainEpisode(
            srcObj.id,
            srcObj.name,
            srcObj.air_date,
            srcObj.episode,
            srcObj.created
        )
}