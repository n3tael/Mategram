package com.xxcactussell.domain

data class MessageForumTopicEdited(
    val name: String,
    val editIconCustomEmojiId: Boolean,
    val iconCustomEmojiId: Long
) : MessageContent
