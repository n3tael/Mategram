package com.xxcactussell.domain

data class ReorderInstalledStickerSets(
    val stickerType: StickerType,
    val stickerSetIds: LongArray
) : Function
