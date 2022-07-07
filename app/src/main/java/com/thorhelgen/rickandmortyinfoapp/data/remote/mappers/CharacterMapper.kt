package com.thorhelgen.rickandmortyinfoapp.data.remote.mappers

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import com.thorhelgen.rickandmortyinfoapp.data.remote.entities.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL
import com.thorhelgen.rickandmortyinfoapp.domain.entities.Character as DomainCharacter

class CharacterMapper() : Mapper<Character, DomainCharacter> {
    override suspend fun map(srcObj: Character): DomainCharacter {
        val characterImage: Bitmap? =
        withContext(
            Dispatchers.IO
        ) {
            try {
                BitmapFactory.decodeStream(URL(srcObj.image).openStream())
            } catch (_: java.net.MalformedURLException) {
                null
            }
        }

        return DomainCharacter(
            srcObj.id,
            srcObj.name,
            srcObj.status,
            srcObj.species,
            srcObj.type,
            srcObj.gender,
            Uri.parse(srcObj.origin.url).lastPathSegment?.let {
                if (it == "") {
                    return@let "0"
                }
                it
            }?.toInt() ?: 0,
            srcObj.origin.name,
            Uri.parse(srcObj.location.url).lastPathSegment?.let{
                if (it == "") {
                    return@let "0"
                }
                it
            }?.toInt() ?: 0,
            srcObj.location.name,
            characterImage,
            srcObj.created
        )
    }
}