package com.xxcactussell.domain

data class SetChatAccentColor(
    val chatId: Long,
    val accentColorId: Int,
    val backgroundCustomEmojiId: Long
) : Function
