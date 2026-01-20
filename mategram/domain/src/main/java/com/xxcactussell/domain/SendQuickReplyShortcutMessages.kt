package com.xxcactussell.domain

data class SendQuickReplyShortcutMessages(
    val chatId: Long,
    val shortcutId: Int,
    val sendingId: Int
) : Function
