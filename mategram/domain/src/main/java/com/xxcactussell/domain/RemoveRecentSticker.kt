package com.xxcactussell.domain

data class RemoveRecentSticker(
    val isAttached: Boolean,
    val sticker: InputFile
) : Function
