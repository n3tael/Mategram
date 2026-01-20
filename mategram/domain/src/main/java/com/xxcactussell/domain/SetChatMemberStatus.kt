package com.xxcactussell.domain

data class SetChatMemberStatus(
    val chatId: Long,
    val memberId: MessageSender,
    val status: ChatMemberStatus
) : Function
