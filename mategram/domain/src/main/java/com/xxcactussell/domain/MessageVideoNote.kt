package com.xxcactussell.domain

data class MessageVideoNote(
    val videoNote: VideoNote,
    val isViewed: Boolean,
    val isSecret: Boolean
) : MessageContent
