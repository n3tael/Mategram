package com.xxcactussell.data.utils.mappers.change

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.ChangeStickerSet.toDomain(): ChangeStickerSet = ChangeStickerSet(
    setId = this.setId,
    isInstalled = this.isInstalled,
    isArchived = this.isArchived
)

