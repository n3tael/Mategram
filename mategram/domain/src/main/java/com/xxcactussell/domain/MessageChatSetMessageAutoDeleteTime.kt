package com.xxcactussell.domain

data class MessageChatSetMessageAutoDeleteTime(
    val messageAutoDeleteTime: Int,
    val fromUserId: Long
) : MessageContent
