package com.xxcactussell.data.utils.mappers.unpin

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun UnpinAllDirectMessagesChatTopicMessages.toData(): TdApi.UnpinAllDirectMessagesChatTopicMessages = TdApi.UnpinAllDirectMessagesChatTopicMessages(
    this.chatId,
    this.topicId
)

