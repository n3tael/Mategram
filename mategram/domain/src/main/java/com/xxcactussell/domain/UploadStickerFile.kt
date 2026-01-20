package com.xxcactussell.domain

data class UploadStickerFile(
    val userId: Long,
    val stickerFormat: StickerFormat,
    val sticker: InputFile
) : Function
