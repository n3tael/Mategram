package com.xxcactussell.data.utils.mappers.upload

import com.xxcactussell.data.utils.mappers.input.toDomain
import com.xxcactussell.data.utils.mappers.sticker.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.UploadStickerFile.toDomain(): UploadStickerFile = UploadStickerFile(
    userId = this.userId,
    stickerFormat = this.stickerFormat.toDomain(),
    sticker = this.sticker.toDomain()
)

