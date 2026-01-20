package com.xxcactussell.domain

data class DeleteMessages(
    val chatId: Long,
    val messageIds: LongArray,
    val revoke: Boolean
) : Function
