package com.xxcactussell.domain

data class DeleteChatHistory(
    val chatId: Long,
    val removeFromChatList: Boolean,
    val revoke: Boolean
) : Function
