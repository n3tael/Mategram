package com.xxcactussell.domain

data class PushMessageContentText(
    val text: String,
    val isPinned: Boolean
) : PushMessageContent
