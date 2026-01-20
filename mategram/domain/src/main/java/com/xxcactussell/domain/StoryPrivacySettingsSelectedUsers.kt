package com.xxcactussell.domain

data class StoryPrivacySettingsSelectedUsers(
    val userIds: LongArray
) : StoryPrivacySettings
