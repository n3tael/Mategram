package com.xxcactussell.domain

data class PushMessageContentVideoNote(
    val videoNote: VideoNote? = null,
    val isPinned: Boolean
) : PushMessageContent
