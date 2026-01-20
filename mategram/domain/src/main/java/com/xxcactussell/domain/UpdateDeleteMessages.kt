package com.xxcactussell.domain

data class UpdateDeleteMessages(
    val chatId: Long,
    val messageIds: LongArray,
    val isPermanent: Boolean,
    val fromCache: Boolean
) : Update
