package com.thorhelgen.rickandmortyinfoapp.data.remote.mappers

import com.thorhelgen.rickandmortyinfoapp.data.remote.entities.Episode
import com.thorhelgen.rickandmortyinfoapp.domain.entities.Episode as DomainEpisode

class EpisodeMapper : Mapper<Episode, DomainEpisode> {
    override suspend fun map(srcObj: Episode): com.thorhelgen.rickandmortyinfoapp.domain.entities.Episode =
        DomainEpisode(
            srcObj.id,
            srcObj.name,
            srcObj.air_date,
            srcObj.episode,
            srcObj.created
        )
}