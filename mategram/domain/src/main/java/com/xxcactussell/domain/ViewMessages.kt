package com.xxcactussell.domain

data class ViewMessages(
    val chatId: Long,
    val messageIds: LongArray,
    val source: MessageSource,
    val forceRead: Boolean
) : Function
