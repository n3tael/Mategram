package com.xxcactussell.domain

data class ChatActiveStories(
    val chatId: Long,
    val list: StoryList? = null,
    val order: Long,
    val canBeArchived: Boolean,
    val maxReadStoryId: Int,
    val stories: List<StoryInfo>
) : Object
