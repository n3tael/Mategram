package com.xxcactussell.domain

data class ChatEvent(
    val id: Long,
    val date: Int,
    val memberId: MessageSender,
    val action: ChatEventAction
) : Object
