package com.xxcactussell.domain

data class ChatEventAccentColorChanged(
    val oldAccentColorId: Int,
    val oldBackgroundCustomEmojiId: Long,
    val newAccentColorId: Int,
    val newBackgroundCustomEmojiId: Long
) : ChatEventAction
