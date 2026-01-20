package com.xxcactussell.domain

data class StoryPrivacySettingsEveryone(
    val exceptUserIds: LongArray
) : StoryPrivacySettings
