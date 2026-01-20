package com.xxcactussell.domain

data class SetChatProfileAccentColor(
    val chatId: Long,
    val profileAccentColorId: Int,
    val profileBackgroundCustomEmojiId: Long
) : Function
