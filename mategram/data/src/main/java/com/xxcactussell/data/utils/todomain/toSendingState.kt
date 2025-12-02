package com.xxcactussell.data.utils

import com.xxcactussell.data.utils.todomain.toDomain
import com.xxcactussell.domain.messages.model.Error
import com.xxcactussell.domain.messages.model.MessageStatus
import org.drinkless.tdlib.TdApi

fun TdApi.MessageSendingState.toDomain() : MessageStatus {
    return when(this) {
        is TdApi.MessageSendingStatePending -> MessageStatus.Pending(sendingId)
        is TdApi.MessageSendingStateFailed -> MessageStatus.Failed(error.toDomain())
        else -> MessageStatus.Failed(Error(0, "Unknown Error"))
    }
}