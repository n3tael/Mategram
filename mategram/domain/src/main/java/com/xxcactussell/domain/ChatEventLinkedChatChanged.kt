package com.xxcactussell.domain

data class ChatEventLinkedChatChanged(
    val oldLinkedChatId: Long,
    val newLinkedChatId: Long
) : ChatEventAction
