package com.thorhelgen.rickandmortyinfoapp.data.remote.mappers

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import com.thorhelgen.rickandmortyinfoapp.data.remote.ApiService
import com.thorhelgen.rickandmortyinfoapp.data.remote.entities.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL
import com.thorhelgen.rickandmortyinfoapp.domain.entities.Character as DomainCharacter

class CharacterMapper(
    private val apiService: ApiService
) : Mapper<Character, DomainCharacter> {
    override suspend fun map(srcObj: Character): DomainCharacter {
        var characterImage: Bitmap? = null
        withContext(
            Dispatchers.IO
        ) {
            characterImage = BitmapFactory.decodeStream(URL(srcObj.image).openStream())
        }

//        Picasso.get().load(srcObj.image).into(object : com.squareup.picasso.Target {
//            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
//                characterImage = bitmap
//            }
//
//            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) = Unit
//            override fun onPrepareLoad(placeHolderDrawable: Drawable?) = Unit
//        })
        return DomainCharacter(
            srcObj.id,
            srcObj.name,
            srcObj.status,
            srcObj.species,
            srcObj.type,
            srcObj.gender,
            Uri.parse(srcObj.origin.url).lastPathSegment?.toInt() ?: 0,
            srcObj.origin.name,
            Uri.parse(srcObj.location.url).lastPathSegment?.toInt() ?: 0,
            srcObj.location.name,
            characterImage,
            srcObj.created
        )
    }
}