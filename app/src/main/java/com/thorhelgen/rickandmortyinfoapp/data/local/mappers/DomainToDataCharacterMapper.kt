package com.thorhelgen.rickandmortyinfoapp.data.local.mappers

import android.content.Context
import android.graphics.Bitmap
import com.thorhelgen.rickandmortyinfoapp.domain.entities.Character as DomainCharacter
import com.thorhelgen.rickandmortyinfoapp.data.local.entities.Character
import kotlin.io.path.Path
import kotlin.io.path.outputStream

class DomainToDataCharacterMapper(
    private val context: Context
) : Mapper<DomainCharacter, Character> {
    override suspend fun map(srcObj: DomainCharacter): Character {
        val imageName = "${srcObj.id}.jpeg"
        srcObj.image?.let {
            val imageOS = Path(context.cacheDir.absolutePath, imageName).outputStream()
            it.compress(Bitmap.CompressFormat.JPEG, 100, imageOS)
            imageOS.close()
        }
        return Character(
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
            srcObj.created
        )
    }
}