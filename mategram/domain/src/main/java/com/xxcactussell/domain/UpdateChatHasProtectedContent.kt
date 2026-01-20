package com.xxcactussell.domain

data class UpdateChatHasProtectedContent(
    val chatId: Long,
    val hasProtectedContent: Boolean
) : Update
