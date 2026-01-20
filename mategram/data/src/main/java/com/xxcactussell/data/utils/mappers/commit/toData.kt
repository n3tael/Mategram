package com.xxcactussell.data.utils.mappers.commit

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun CommitPendingPaidMessageReactions.toData(): TdApi.CommitPendingPaidMessageReactions = TdApi.CommitPendingPaidMessageReactions(
    this.chatId,
    this.messageId
)

