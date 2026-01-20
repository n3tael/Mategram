package com.xxcactussell.domain

data class MessageVideoChatScheduled(
    val groupCallId: Int,
    val startDate: Int
) : MessageContent
