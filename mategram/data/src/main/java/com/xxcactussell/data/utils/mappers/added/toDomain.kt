package com.xxcactussell.data.utils.mappers.added

import com.xxcactussell.data.utils.mappers.message.toDomain
import com.xxcactussell.data.utils.mappers.reaction.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.AddedReaction.toDomain(): AddedReaction = AddedReaction(
    type = this.type.toDomain(),
    senderId = this.senderId.toDomain(),
    isOutgoing = this.isOutgoing,
    date = this.date
)

fun TdApi.AddedReactions.toDomain(): AddedReactions = AddedReactions(
    totalCount = this.totalCount,
    reactions = this.reactions.map { it.toDomain() },
    nextOffset = this.nextOffset
)

