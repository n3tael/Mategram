package com.xxcactussell.domain

data class PushMessageContentGame(
    val title: String,
    val isPinned: Boolean
) : PushMessageContent
