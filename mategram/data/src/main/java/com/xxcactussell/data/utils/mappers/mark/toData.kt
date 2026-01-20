package com.xxcactussell.data.utils.mappers.mark

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun MarkChecklistTasksAsDone.toData(): TdApi.MarkChecklistTasksAsDone = TdApi.MarkChecklistTasksAsDone(
    this.chatId,
    this.messageId,
    this.markedAsDoneTaskIds,
    this.markedAsNotDoneTaskIds
)

