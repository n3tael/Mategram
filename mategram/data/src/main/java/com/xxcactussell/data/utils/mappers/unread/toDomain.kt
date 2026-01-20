package com.xxcactussell.data.utils.mappers.unread

import com.xxcactussell.data.utils.mappers.message.toDomain
import com.xxcactussell.data.utils.mappers.reaction.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.UnreadReaction.toDomain(): UnreadReaction = UnreadReaction(
    type = this.type.toDomain(),
    senderId = this.senderId.toDomain(),
    isBig = this.isBig
)

