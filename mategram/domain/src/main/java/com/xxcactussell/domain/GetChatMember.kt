package com.xxcactussell.domain

data class GetChatMember(
    val chatId: Long,
    val memberId: MessageSender
) : Function
