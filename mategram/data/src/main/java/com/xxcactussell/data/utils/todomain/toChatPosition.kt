package com.xxcactussell.data.utils

import com.xxcactussell.domain.chats.model.ChatPosition
import org.drinkless.tdlib.TdApi

fun TdApi.ChatPosition.toDomain() : ChatPosition {
    return ChatPosition(list.toDomain(), order, isPinned)
}