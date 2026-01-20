package com.xxcactussell.domain

data class GetOwnedStickerSets(
    val offsetStickerSetId: Long,
    val limit: Int
) : Function
