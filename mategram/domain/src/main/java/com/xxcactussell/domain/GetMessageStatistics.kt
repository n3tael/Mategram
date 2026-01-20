package com.xxcactussell.domain

data class GetMessageStatistics(
    val chatId: Long,
    val messageId: Long,
    val isDark: Boolean
) : Function
