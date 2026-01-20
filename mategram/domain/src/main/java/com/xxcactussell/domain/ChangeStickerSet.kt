package com.xxcactussell.domain

data class ChangeStickerSet(
    val setId: Long,
    val isInstalled: Boolean,
    val isArchived: Boolean
) : Function
