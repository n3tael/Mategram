package com.xxcactussell.data.utils.mappers.upload

import com.xxcactussell.data.utils.mappers.input.toData
import com.xxcactussell.data.utils.mappers.sticker.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun UploadStickerFile.toData(): TdApi.UploadStickerFile = TdApi.UploadStickerFile(
    this.userId,
    this.stickerFormat.toData(),
    this.sticker.toData()
)

