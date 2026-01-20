package com.xxcactussell.domain

data class UpdateChatEmojiStatus(
    val chatId: Long,
    val emojiStatus: EmojiStatus? = null
) : Update
