package com.xxcactussell.domain

data class SearchChatMembers(
    val chatId: Long,
    val query: String,
    val limit: Int,
    val filter: ChatMembersFilter
) : Function
