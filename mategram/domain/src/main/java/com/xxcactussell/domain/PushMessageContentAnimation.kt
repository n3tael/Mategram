package com.xxcactussell.domain

data class PushMessageContentAnimation(
    val animation: Animation? = null,
    val caption: String,
    val isPinned: Boolean
) : PushMessageContent
