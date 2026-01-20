package com.xxcactussell.domain

data class LoadDirectMessagesChatTopics(
    val chatId: Long,
    val limit: Int
) : Function
