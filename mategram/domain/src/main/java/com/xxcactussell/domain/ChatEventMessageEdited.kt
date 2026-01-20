package com.xxcactussell.domain

data class ChatEventMessageEdited(
    val oldMessage: Message,
    val newMessage: Message
) : ChatEventAction
