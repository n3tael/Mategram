package com.xxcactussell.domain

data class PinChatMessage(
    val chatId: Long,
    val messageId: Long,
    val disableNotification: Boolean,
    val onlyForSelf: Boolean
) : Function
