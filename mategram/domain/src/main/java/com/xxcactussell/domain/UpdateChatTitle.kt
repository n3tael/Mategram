package com.xxcactussell.domain

data class UpdateChatTitle(
    val chatId: Long,
    val title: String
) : Update
