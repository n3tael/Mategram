package com.xxcactussell.data.utils

import com.xxcactussell.domain.messages.model.MessageSender
import org.drinkless.tdlib.TdApi

fun TdApi.MessageSender.toDomain() : MessageSender {
    return when (this.constructor) {
        TdApi.MessageSenderChat.CONSTRUCTOR -> MessageSender.Chat((this as TdApi.MessageSenderChat).chatId)
        TdApi.MessageSenderUser.CONSTRUCTOR -> MessageSender.User((this as TdApi.MessageSenderUser).userId)
        else -> MessageSender.Unknown
    }
}