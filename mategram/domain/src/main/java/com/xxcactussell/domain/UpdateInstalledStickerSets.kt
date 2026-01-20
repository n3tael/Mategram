package com.xxcactussell.domain

data class UpdateInstalledStickerSets(
    val stickerType: StickerType,
    val stickerSetIds: LongArray
) : Update
