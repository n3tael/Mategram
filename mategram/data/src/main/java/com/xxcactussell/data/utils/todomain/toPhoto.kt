package com.xxcactussell.data.utils

import com.xxcactussell.domain.messages.model.Photo
import com.xxcactussell.domain.messages.model.PhotoSize
import com.xxcactussell.domain.messages.model.MiniThumbnail
import org.drinkless.tdlib.TdApi

fun TdApi.Photo.toDomain(): Photo {
    val sizes = mutableListOf<PhotoSize>()
    this.sizes.forEach {
        sizes.add(PhotoSize(
            it.type,
            it.photo.toDomain(),
            it.width,
            it.height
        )
        )
    }
    return Photo(
        hasStickers = this.hasStickers,
        miniThumbnail = this.minithumbnail?.toDomain(),
        sizes = sizes
    )
}

fun TdApi.Minithumbnail.toDomain() : MiniThumbnail {
    return MiniThumbnail(
        height,
        width,
        data
    )
}