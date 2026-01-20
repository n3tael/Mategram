package com.xxcactussell.domain

data class PushMessageContentAudio(
    val audio: Audio? = null,
    val isPinned: Boolean
) : PushMessageContent
