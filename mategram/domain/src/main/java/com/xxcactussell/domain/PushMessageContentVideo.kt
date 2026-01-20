package com.xxcactussell.domain

data class PushMessageContentVideo(
    val video: Video? = null,
    val caption: String,
    val isSecret: Boolean,
    val isPinned: Boolean
) : PushMessageContent
