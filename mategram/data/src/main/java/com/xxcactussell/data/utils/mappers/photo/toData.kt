package com.xxcactussell.data.utils.mappers.photo

import com.xxcactussell.data.utils.mappers.file.toData
import com.xxcactussell.data.utils.mappers.minithumbnail.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun Photo.toData(): TdApi.Photo = TdApi.Photo(
    this.hasStickers,
    this.minithumbnail?.toData(),
    this.sizes.map { it.toData() }.toTypedArray()
)

