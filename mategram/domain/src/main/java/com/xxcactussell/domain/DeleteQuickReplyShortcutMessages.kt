package com.xxcactussell.domain

data class DeleteQuickReplyShortcutMessages(
    val shortcutId: Int,
    val messageIds: LongArray
) : Function
