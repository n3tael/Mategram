package com.xxcactussell.data.utils.mappers.click

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun ClickAnimatedEmojiMessage.toData(): TdApi.ClickAnimatedEmojiMessage = TdApi.ClickAnimatedEmojiMessage(
    this.chatId,
    this.messageId
)

fun ClickChatSponsoredMessage.toData(): TdApi.ClickChatSponsoredMessage = TdApi.ClickChatSponsoredMessage(
    this.chatId,
    this.messageId,
    this.isMediaClick,
    this.fromFullscreen
)

fun ClickPremiumSubscriptionButton.toData(): TdApi.ClickPremiumSubscriptionButton = TdApi.ClickPremiumSubscriptionButton(
)

fun ClickVideoMessageAdvertisement.toData(): TdApi.ClickVideoMessageAdvertisement = TdApi.ClickVideoMessageAdvertisement(
    this.advertisementUniqueId
)

