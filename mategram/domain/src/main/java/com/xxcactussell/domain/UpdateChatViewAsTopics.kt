package com.xxcactussell.domain

data class UpdateChatViewAsTopics(
    val chatId: Long,
    val viewAsTopics: Boolean
) : Update
