package com.xxcactussell.domain

data class ChatEventVideoChatEnded(
    val groupCallId: Int
) : ChatEventAction
