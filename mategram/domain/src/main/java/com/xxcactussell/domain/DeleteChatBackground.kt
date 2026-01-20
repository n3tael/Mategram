package com.xxcactussell.domain

data class DeleteChatBackground(
    val chatId: Long,
    val restorePrevious: Boolean
) : Function
