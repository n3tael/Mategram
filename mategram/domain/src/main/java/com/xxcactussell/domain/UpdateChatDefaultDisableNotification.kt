package com.xxcactussell.domain

data class UpdateChatDefaultDisableNotification(
    val chatId: Long,
    val defaultDisableNotification: Boolean
) : Update
