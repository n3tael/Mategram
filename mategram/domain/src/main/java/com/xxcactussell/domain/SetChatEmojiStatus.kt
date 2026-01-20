package com.xxcactussell.domain

data class SetChatEmojiStatus(
    val chatId: Long,
    val emojiStatus: EmojiStatus
) : Function
