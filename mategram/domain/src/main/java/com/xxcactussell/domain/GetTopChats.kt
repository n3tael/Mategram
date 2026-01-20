package com.xxcactussell.domain

data class GetTopChats(
    val category: TopChatCategory,
    val limit: Int
) : Function
