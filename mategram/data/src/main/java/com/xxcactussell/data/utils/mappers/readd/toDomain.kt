package com.xxcactussell.data.utils.mappers.readd

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.ReaddQuickReplyShortcutMessages.toDomain(): ReaddQuickReplyShortcutMessages = ReaddQuickReplyShortcutMessages(
    shortcutName = this.shortcutName,
    messageIds = this.messageIds
)

