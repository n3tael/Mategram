package com.xxcactussell.domain

data class ChatEventProfileAccentColorChanged(
    val oldProfileAccentColorId: Int,
    val oldProfileBackgroundCustomEmojiId: Long,
    val newProfileAccentColorId: Int,
    val newProfileBackgroundCustomEmojiId: Long
) : ChatEventAction
