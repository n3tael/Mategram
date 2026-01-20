package com.xxcactussell.domain

data class GetChatEventLog(
    val chatId: Long,
    val query: String,
    val fromEventId: Long,
    val limit: Int,
    val filters: ChatEventLogFilters,
    val userIds: LongArray
) : Function
