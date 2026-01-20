package com.xxcactussell.domain

data class BanChatMember(
    val chatId: Long,
    val memberId: MessageSender,
    val bannedUntilDate: Int,
    val revokeMessages: Boolean
) : Function
