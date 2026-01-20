package com.xxcactussell.domain

data class PushMessageContentChecklist(
    val title: String,
    val isPinned: Boolean
) : PushMessageContent
