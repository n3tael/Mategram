package com.xxcactussell.data.utils.mappers.readd

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun ReaddQuickReplyShortcutMessages.toData(): TdApi.ReaddQuickReplyShortcutMessages = TdApi.ReaddQuickReplyShortcutMessages(
    this.shortcutName,
    this.messageIds
)

