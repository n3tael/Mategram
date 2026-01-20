package com.xxcactussell.domain

data class PushMessageContentLocation(
    val isLive: Boolean,
    val isPinned: Boolean
) : PushMessageContent
