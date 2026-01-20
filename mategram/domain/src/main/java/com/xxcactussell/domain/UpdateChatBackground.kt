package com.xxcactussell.domain

data class UpdateChatBackground(
    val chatId: Long,
    val background: ChatBackground? = null
) : Update
