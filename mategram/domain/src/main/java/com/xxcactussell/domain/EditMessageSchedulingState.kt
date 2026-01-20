package com.xxcactussell.domain

data class EditMessageSchedulingState(
    val chatId: Long,
    val messageId: Long,
    val schedulingState: MessageSchedulingState
) : Function
