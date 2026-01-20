package com.xxcactussell.domain

data class ToggleChatDefaultDisableNotification(
    val chatId: Long,
    val defaultDisableNotification: Boolean
) : Function
