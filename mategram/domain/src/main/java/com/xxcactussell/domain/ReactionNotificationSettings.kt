package com.xxcactussell.domain

data class ReactionNotificationSettings(
    val messageReactionSource: ReactionNotificationSource,
    val storyReactionSource: ReactionNotificationSource,
    val soundId: Long,
    val showPreview: Boolean
) : Object
