package com.xxcactussell.data.utils.mappers.unread

import com.xxcactussell.data.utils.mappers.message.toData
import com.xxcactussell.data.utils.mappers.reaction.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun UnreadReaction.toData(): TdApi.UnreadReaction = TdApi.UnreadReaction(
    this.type.toData(),
    this.senderId.toData(),
    this.isBig
)

