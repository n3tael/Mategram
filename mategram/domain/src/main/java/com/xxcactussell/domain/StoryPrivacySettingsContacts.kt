package com.xxcactussell.domain

data class StoryPrivacySettingsContacts(
    val exceptUserIds: LongArray
) : StoryPrivacySettings
