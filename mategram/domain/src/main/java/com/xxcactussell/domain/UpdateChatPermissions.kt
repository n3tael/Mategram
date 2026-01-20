package com.xxcactussell.domain

data class UpdateChatPermissions(
    val chatId: Long,
    val permissions: ChatPermissions
) : Update
