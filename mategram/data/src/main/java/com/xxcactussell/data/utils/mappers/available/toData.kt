package com.xxcactussell.data.utils.mappers.available

import com.xxcactussell.data.utils.mappers.monetization.toData
import com.xxcactussell.data.utils.mappers.reaction.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun AvailableGift.toData(): TdApi.AvailableGift = TdApi.AvailableGift(
    this.gift.toData(),
    this.resaleCount,
    this.minResaleStarCount,
    this.title
)

fun AvailableGifts.toData(): TdApi.AvailableGifts = TdApi.AvailableGifts(
    this.gifts.map { it.toData() }.toTypedArray()
)

fun AvailableReaction.toData(): TdApi.AvailableReaction = TdApi.AvailableReaction(
    this.type.toData(),
    this.needsPremium
)

fun AvailableReactions.toData(): TdApi.AvailableReactions = TdApi.AvailableReactions(
    this.topReactions.map { it.toData() }.toTypedArray(),
    this.recentReactions.map { it.toData() }.toTypedArray(),
    this.popularReactions.map { it.toData() }.toTypedArray(),
    this.allowCustomEmoji,
    this.areTags,
    this.unavailabilityReason?.toData()
)

