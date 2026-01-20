package com.xxcactussell.domain

data class UpdateStoryListChatCount(
    val storyList: StoryList,
    val chatCount: Int
) : Update
