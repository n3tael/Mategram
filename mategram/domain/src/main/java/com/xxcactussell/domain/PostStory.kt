package com.xxcactussell.domain

data class PostStory(
    val chatId: Long,
    val content: InputStoryContent,
    val areas: InputStoryAreas,
    val caption: FormattedText,
    val privacySettings: StoryPrivacySettings,
    val albumIds: IntArray,
    val activePeriod: Int,
    val fromStoryFullId: StoryFullId,
    val isPostedToChatPage: Boolean,
    val protectContent: Boolean
) : Function
