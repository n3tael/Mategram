package com.xxcactussell.domain

data class UpdateChatIsMarkedAsUnread(
    val chatId: Long,
    val isMarkedAsUnread: Boolean
) : Update
