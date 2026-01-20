package com.xxcactussell.domain

data class UpdateMessageLiveLocationViewed(
    val chatId: Long,
    val messageId: Long
) : Update
