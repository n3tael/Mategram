package com.xxcactussell.repositories.messages.utils

import com.xxcactussell.domain.MessageSender
import com.xxcactussell.domain.MessageSenderChat
import com.xxcactussell.domain.MessageSenderUser

fun MessageSender.getId() : Long? {
    return when (this) {
        is MessageSenderChat -> this.chatId
        is MessageSenderUser -> this.userId
    }
}