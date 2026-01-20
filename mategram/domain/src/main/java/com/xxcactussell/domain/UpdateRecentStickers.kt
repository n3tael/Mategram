package com.xxcactussell.domain

data class UpdateRecentStickers(
    val isAttached: Boolean,
    val stickerIds: IntArray
) : Update
