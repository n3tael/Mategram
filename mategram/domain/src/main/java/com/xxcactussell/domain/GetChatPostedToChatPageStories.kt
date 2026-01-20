package com.xxcactussell.domain

data class GetChatPostedToChatPageStories(
    val chatId: Long,
    val fromStoryId: Int,
    val limit: Int
) : Function
