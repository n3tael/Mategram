package com.xxcactussell.domain

data class LinkPreviewTypeVideoChat(
    val photo: ChatPhoto? = null,
    val isLiveStream: Boolean,
    val joinsAsSpeaker: Boolean
) : LinkPreviewType
