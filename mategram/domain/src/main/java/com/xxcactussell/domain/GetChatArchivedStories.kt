package com.xxcactussell.domain

data class GetChatArchivedStories(
    val chatId: Long,
    val fromStoryId: Int,
    val limit: Int
) : Function
