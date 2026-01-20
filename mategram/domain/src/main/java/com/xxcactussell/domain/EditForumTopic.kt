package com.xxcactussell.domain

data class EditForumTopic(
    val chatId: Long,
    val messageThreadId: Long,
    val name: String,
    val editIconCustomEmoji: Boolean,
    val iconCustomEmojiId: Long
) : Function
