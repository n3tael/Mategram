package com.xxcactussell.domain

data class SetUserEmojiStatus(
    val userId: Long,
    val emojiStatus: EmojiStatus
) : Function
