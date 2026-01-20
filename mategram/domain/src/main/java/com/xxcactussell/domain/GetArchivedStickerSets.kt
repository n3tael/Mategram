package com.xxcactussell.domain

data class GetArchivedStickerSets(
    val stickerType: StickerType,
    val offsetStickerSetId: Long,
    val limit: Int
) : Function
