package com.xxcactussell.data.utils.mappers.telegram

import com.xxcactussell.data.utils.mappers.formatted.toDomain
import com.xxcactussell.data.utils.mappers.giveaway.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.TelegramPaymentPurpose.toDomain(): TelegramPaymentPurpose = when(this) {
    is TdApi.TelegramPaymentPurposePremiumGift -> this.toDomain()
    is TdApi.TelegramPaymentPurposePremiumGiftCodes -> this.toDomain()
    is TdApi.TelegramPaymentPurposePremiumGiveaway -> this.toDomain()
    is TdApi.TelegramPaymentPurposeStars -> this.toDomain()
    is TdApi.TelegramPaymentPurposeGiftedStars -> this.toDomain()
    is TdApi.TelegramPaymentPurposeStarGiveaway -> this.toDomain()
    is TdApi.TelegramPaymentPurposeJoinChat -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.TelegramPaymentPurposeGiftedStars.toDomain(): TelegramPaymentPurposeGiftedStars = TelegramPaymentPurposeGiftedStars(
    userId = this.userId,
    currency = this.currency,
    amount = this.amount,
    starCount = this.starCount
)

fun TdApi.TelegramPaymentPurposeJoinChat.toDomain(): TelegramPaymentPurposeJoinChat = TelegramPaymentPurposeJoinChat(
    inviteLink = this.inviteLink
)

fun TdApi.TelegramPaymentPurposePremiumGift.toDomain(): TelegramPaymentPurposePremiumGift = TelegramPaymentPurposePremiumGift(
    currency = this.currency,
    amount = this.amount,
    userId = this.userId,
    monthCount = this.monthCount,
    text = this.text.toDomain()
)

fun TdApi.TelegramPaymentPurposePremiumGiftCodes.toDomain(): TelegramPaymentPurposePremiumGiftCodes = TelegramPaymentPurposePremiumGiftCodes(
    boostedChatId = this.boostedChatId,
    currency = this.currency,
    amount = this.amount,
    userIds = this.userIds,
    monthCount = this.monthCount,
    text = this.text.toDomain()
)

fun TdApi.TelegramPaymentPurposePremiumGiveaway.toDomain(): TelegramPaymentPurposePremiumGiveaway = TelegramPaymentPurposePremiumGiveaway(
    parameters = this.parameters.toDomain(),
    currency = this.currency,
    amount = this.amount,
    winnerCount = this.winnerCount,
    monthCount = this.monthCount
)

fun TdApi.TelegramPaymentPurposeStarGiveaway.toDomain(): TelegramPaymentPurposeStarGiveaway = TelegramPaymentPurposeStarGiveaway(
    parameters = this.parameters.toDomain(),
    currency = this.currency,
    amount = this.amount,
    winnerCount = this.winnerCount,
    starCount = this.starCount
)

fun TdApi.TelegramPaymentPurposeStars.toDomain(): TelegramPaymentPurposeStars = TelegramPaymentPurposeStars(
    currency = this.currency,
    amount = this.amount,
    starCount = this.starCount,
    chatId = this.chatId
)

