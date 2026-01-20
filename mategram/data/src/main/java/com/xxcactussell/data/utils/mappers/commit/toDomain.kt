package com.xxcactussell.data.utils.mappers.commit

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.CommitPendingPaidMessageReactions.toDomain(): CommitPendingPaidMessageReactions = CommitPendingPaidMessageReactions(
    chatId = this.chatId,
    messageId = this.messageId
)

