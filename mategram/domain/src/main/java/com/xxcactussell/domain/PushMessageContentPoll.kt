package com.xxcactussell.domain

data class PushMessageContentPoll(
    val question: String,
    val isRegular: Boolean,
    val isPinned: Boolean
) : PushMessageContent
