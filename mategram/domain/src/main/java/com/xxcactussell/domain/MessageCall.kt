package com.xxcactussell.domain

data class MessageCall(
    val isVideo: Boolean,
    val discardReason: CallDiscardReason,
    val duration: Int
) : MessageContent
