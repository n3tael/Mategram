package com.xxcactussell.data.utils

import com.xxcactussell.data.utils.todomain.toDomain
import com.xxcactussell.domain.messages.model.Message
import org.drinkless.tdlib.TdApi

fun TdApi.Message.toDomain() : Message {
    return Message(
        id,
        chatId,
        senderId.toDomain(),
        sendingState?.toDomain(),
        isOutgoing,
        isPinned,
        content.toDomain(),
        date,
        editDate,
        authorSignature,
        mediaAlbumId,
        containsUnreadMention,
        isChannelPost,
        canBeSaved,
        messageThreadId,
        selfDestructIn,
        autoDeleteIn
    )
}