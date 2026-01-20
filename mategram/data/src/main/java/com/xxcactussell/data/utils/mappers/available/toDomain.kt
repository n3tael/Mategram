package com.xxcactussell.data.utils.mappers.available

import com.xxcactussell.data.utils.mappers.monetization.toDomain
import com.xxcactussell.data.utils.mappers.reaction.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.AvailableGift.toDomain(): AvailableGift = AvailableGift(
    gift = this.gift.toDomain(),
    resaleCount = this.resaleCount,
    minResaleStarCount = this.minResaleStarCount,
    title = this.title
)

fun TdApi.AvailableGifts.toDomain(): AvailableGifts = AvailableGifts(
    gifts = this.gifts.map { it.toDomain() }
)

fun TdApi.AvailableReaction.toDomain(): AvailableReaction = AvailableReaction(
    type = this.type.toDomain(),
    needsPremium = this.needsPremium
)

fun TdApi.AvailableReactions.toDomain(): AvailableReactions = AvailableReactions(
    topReactions = this.topReactions.map { it.toDomain() },
    recentReactions = this.recentReactions.map { it.toDomain() },
    popularReactions = this.popularReactions.map { it.toDomain() },
    allowCustomEmoji = this.allowCustomEmoji,
    areTags = this.areTags,
    unavailabilityReason = this.unavailabilityReason?.toDomain()
)

