package com.xxcactussell.domain

data class UpdateVideoPublished(
    val chatId: Long,
    val messageId: Long
) : Update
