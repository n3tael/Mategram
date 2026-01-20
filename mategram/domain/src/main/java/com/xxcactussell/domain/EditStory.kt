package com.xxcactussell.domain

data class EditStory(
    val storyPosterChatId: Long,
    val storyId: Int,
    val content: InputStoryContent,
    val areas: InputStoryAreas,
    val caption: FormattedText
) : Function
