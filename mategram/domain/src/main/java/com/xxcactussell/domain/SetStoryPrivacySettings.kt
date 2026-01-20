package com.xxcactussell.domain

data class SetStoryPrivacySettings(
    val storyId: Int,
    val privacySettings: StoryPrivacySettings
) : Function
