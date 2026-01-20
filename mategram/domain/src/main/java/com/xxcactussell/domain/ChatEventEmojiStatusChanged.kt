package com.xxcactussell.domain

data class ChatEventEmojiStatusChanged(
    val oldEmojiStatus: EmojiStatus? = null,
    val newEmojiStatus: EmojiStatus? = null
) : ChatEventAction
