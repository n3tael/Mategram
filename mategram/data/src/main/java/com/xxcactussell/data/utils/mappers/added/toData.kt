package com.xxcactussell.data.utils.mappers.added

import com.xxcactussell.data.utils.mappers.message.toData
import com.xxcactussell.data.utils.mappers.reaction.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun AddedReaction.toData(): TdApi.AddedReaction = TdApi.AddedReaction(
    this.type.toData(),
    this.senderId.toData(),
    this.isOutgoing,
    this.date
)

fun AddedReactions.toData(): TdApi.AddedReactions = TdApi.AddedReactions(
    this.totalCount,
    this.reactions.map { it.toData() }.toTypedArray(),
    this.nextOffset
)

