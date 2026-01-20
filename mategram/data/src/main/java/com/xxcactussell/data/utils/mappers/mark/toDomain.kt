package com.xxcactussell.data.utils.mappers.mark

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.MarkChecklistTasksAsDone.toDomain(): MarkChecklistTasksAsDone = MarkChecklistTasksAsDone(
    chatId = this.chatId,
    messageId = this.messageId,
    markedAsDoneTaskIds = this.markedAsDoneTaskIds,
    markedAsNotDoneTaskIds = this.markedAsNotDoneTaskIds
)

