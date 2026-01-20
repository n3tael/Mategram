package com.xxcactussell.data.utils.mappers.change

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun ChangeStickerSet.toData(): TdApi.ChangeStickerSet = TdApi.ChangeStickerSet(
    this.setId,
    this.isInstalled,
    this.isArchived
)

