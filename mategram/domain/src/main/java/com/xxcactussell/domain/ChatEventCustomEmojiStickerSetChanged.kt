package com.xxcactussell.domain

data class ChatEventCustomEmojiStickerSetChanged(
    val oldStickerSetId: Long,
    val newStickerSetId: Long
) : ChatEventAction
