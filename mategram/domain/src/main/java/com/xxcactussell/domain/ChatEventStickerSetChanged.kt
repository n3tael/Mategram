package com.xxcactussell.domain

data class ChatEventStickerSetChanged(
    val oldStickerSetId: Long,
    val newStickerSetId: Long
) : ChatEventAction
