package com.thorhelgen.rickandmortyinfoapp.data.local.mappers

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.thorhelgen.rickandmortyinfoapp.domain.entities.Character as DomainCharacter
import com.thorhelgen.rickandmortyinfoapp.data.local.entities.Character
import kotlin.io.path.Path
import kotlin.io.path.exists
import kotlin.io.path.inputStream
import kotlin.io.path.outputStream

class DataToDomainCharacterMapper(
    private val context: Context
) : Mapper<Character, DomainCharacter> {
    override suspend fun map(srcObj: Character): DomainCharacter {
        val imagePath = Path(context.cacheDir.absolutePath, "${srcObj.id}.jpeg")
        var image: Bitmap? = null
        if (imagePath.exists()) {
            image = BitmapFactory.decodeStream(imagePath.inputStream())
        }
        return DomainCharacter(
            srcObj.id,
            srcObj.name,
            srcObj.status,
            srcObj.species,
            srcObj.type,
            srcObj.gender,
            srcObj.originId,
            srcObj.originName,
            srcObj.locationId,
            srcObj.locationName,
            image,
            srcObj.created
        )
    }
}