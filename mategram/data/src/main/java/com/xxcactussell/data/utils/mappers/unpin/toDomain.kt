package com.xxcactussell.data.utils.mappers.unpin

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.UnpinAllDirectMessagesChatTopicMessages.toDomain(): UnpinAllDirectMessagesChatTopicMessages = UnpinAllDirectMessagesChatTopicMessages(
    chatId = this.chatId,
    topicId = this.topicId
)

