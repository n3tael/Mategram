package com.xxcactussell.domain

data class SetChatActiveStoriesList(
    val chatId: Long,
    val storyList: StoryList
) : Function
