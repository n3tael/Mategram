package com.xxcactussell.domain

data class RemoveTopChat(
    val category: TopChatCategory,
    val chatId: Long
) : Function
