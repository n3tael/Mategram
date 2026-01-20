package com.xxcactussell.domain

data class SetChatPermissions(
    val chatId: Long,
    val permissions: ChatPermissions
) : Function
