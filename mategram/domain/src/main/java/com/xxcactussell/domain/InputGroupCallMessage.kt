package com.xxcactussell.domain

data class InputGroupCallMessage(
    val chatId: Long,
    val messageId: Long
) : InputGroupCall
