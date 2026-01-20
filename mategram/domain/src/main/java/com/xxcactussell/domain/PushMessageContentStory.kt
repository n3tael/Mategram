package com.xxcactussell.domain

data class PushMessageContentStory(
    val isMention: Boolean,
    val isPinned: Boolean
) : PushMessageContent
