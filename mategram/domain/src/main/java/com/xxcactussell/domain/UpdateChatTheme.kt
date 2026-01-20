package com.xxcactussell.domain

data class UpdateChatTheme(
    val chatId: Long,
    val theme: ChatTheme? = null
) : Update
