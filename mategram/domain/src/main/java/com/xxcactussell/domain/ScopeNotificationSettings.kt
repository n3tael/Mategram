package com.xxcactussell.domain

data class ScopeNotificationSettings(
    val muteFor: Int,
    val soundId: Long,
    val showPreview: Boolean,
    val useDefaultMuteStories: Boolean,
    val muteStories: Boolean,
    val storySoundId: Long,
    val showStoryPoster: Boolean,
    val disablePinnedMessageNotifications: Boolean,
    val disableMentionNotifications: Boolean
) : Object
