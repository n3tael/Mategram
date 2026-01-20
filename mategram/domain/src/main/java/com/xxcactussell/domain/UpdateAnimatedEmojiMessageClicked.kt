package com.xxcactussell.domain

data class UpdateAnimatedEmojiMessageClicked(
    val chatId: Long,
    val messageId: Long,
    val sticker: Sticker
) : Update
