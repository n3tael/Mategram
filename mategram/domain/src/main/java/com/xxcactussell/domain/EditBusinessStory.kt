package com.xxcactussell.domain

data class EditBusinessStory(
    val storyPosterChatId: Long,
    val storyId: Int,
    val content: InputStoryContent,
    val areas: InputStoryAreas,
    val caption: FormattedText,
    val privacySettings: StoryPrivacySettings
) : Function
