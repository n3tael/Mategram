package com.xxcactussell.data.utils.mappers.photo

import com.xxcactussell.data.utils.mappers.file.toDomain
import com.xxcactussell.data.utils.mappers.minithumbnail.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.Photo.toDomain(): Photo = Photo(
    hasStickers = this.hasStickers,
    minithumbnail = this.minithumbnail?.toDomain(),
    sizes = this.sizes.map { it.toDomain() }
)

