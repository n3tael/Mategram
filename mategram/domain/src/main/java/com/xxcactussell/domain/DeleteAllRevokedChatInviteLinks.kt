package com.xxcactussell.domain

data class DeleteAllRevokedChatInviteLinks(
    val chatId: Long,
    val creatorUserId: Long
) : Function
