package com.xxcactussell.domain

data class AddRecentSticker(
    val isAttached: Boolean,
    val sticker: InputFile
) : Function
