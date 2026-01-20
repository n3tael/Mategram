package com.xxcactussell.domain

data class UpdateMessageContentOpened(
    val chatId: Long,
    val messageId: Long
) : Update
