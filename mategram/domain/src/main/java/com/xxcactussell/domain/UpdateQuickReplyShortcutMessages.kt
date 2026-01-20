package com.xxcactussell.domain

data class UpdateQuickReplyShortcutMessages(
    val shortcutId: Int,
    val messages: List<QuickReplyMessage>
) : Update
