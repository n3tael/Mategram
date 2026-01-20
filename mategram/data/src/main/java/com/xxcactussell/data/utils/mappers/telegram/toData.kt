package com.xxcactussell.data.utils.mappers.telegram

import com.xxcactussell.data.utils.mappers.formatted.toData
import com.xxcactussell.data.utils.mappers.giveaway.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun TelegramPaymentPurpose.toData(): TdApi.TelegramPaymentPurpose = when(this) {
    is TelegramPaymentPurposePremiumGift -> this.toData()
    is TelegramPaymentPurposePremiumGiftCodes -> this.toData()
    is TelegramPaymentPurposePremiumGiveaway -> this.toData()
    is TelegramPaymentPurposeStars -> this.toData()
    is TelegramPaymentPurposeGiftedStars -> this.toData()
    is TelegramPaymentPurposeStarGiveaway -> this.toData()
    is TelegramPaymentPurposeJoinChat -> this.toData()
}

fun TelegramPaymentPurposeGiftedStars.toData(): TdApi.TelegramPaymentPurposeGiftedStars = TdApi.TelegramPaymentPurposeGiftedStars(
    this.userId,
    this.currency,
    this.amount,
    this.starCount
)

fun TelegramPaymentPurposeJoinChat.toData(): TdApi.TelegramPaymentPurposeJoinChat = TdApi.TelegramPaymentPurposeJoinChat(
    this.inviteLink
)

fun TelegramPaymentPurposePremiumGift.toData(): TdApi.TelegramPaymentPurposePremiumGift = TdApi.TelegramPaymentPurposePremiumGift(
    this.currency,
    this.amount,
    this.userId,
    this.monthCount,
    this.text.toData()
)

fun TelegramPaymentPurposePremiumGiftCodes.toData(): TdApi.TelegramPaymentPurposePremiumGiftCodes = TdApi.TelegramPaymentPurposePremiumGiftCodes(
    this.boostedChatId,
    this.currency,
    this.amount,
    this.userIds,
    this.monthCount,
    this.text.toData()
)

fun TelegramPaymentPurposePremiumGiveaway.toData(): TdApi.TelegramPaymentPurposePremiumGiveaway = TdApi.TelegramPaymentPurposePremiumGiveaway(
    this.parameters.toData(),
    this.currency,
    this.amount,
    this.winnerCount,
    this.monthCount
)

fun TelegramPaymentPurposeStarGiveaway.toData(): TdApi.TelegramPaymentPurposeStarGiveaway = TdApi.TelegramPaymentPurposeStarGiveaway(
    this.parameters.toData(),
    this.currency,
    this.amount,
    this.winnerCount,
    this.starCount
)

fun TelegramPaymentPurposeStars.toData(): TdApi.TelegramPaymentPurposeStars = TdApi.TelegramPaymentPurposeStars(
    this.currency,
    this.amount,
    this.starCount,
    this.chatId
)

