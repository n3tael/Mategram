package com.xxcactussell.data.utils.mappers.click

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.ClickAnimatedEmojiMessage.toDomain(): ClickAnimatedEmojiMessage = ClickAnimatedEmojiMessage(
    chatId = this.chatId,
    messageId = this.messageId
)

fun TdApi.ClickChatSponsoredMessage.toDomain(): ClickChatSponsoredMessage = ClickChatSponsoredMessage(
    chatId = this.chatId,
    messageId = this.messageId,
    isMediaClick = this.isMediaClick,
    fromFullscreen = this.fromFullscreen
)

fun TdApi.ClickPremiumSubscriptionButton.toDomain(): ClickPremiumSubscriptionButton = ClickPremiumSubscriptionButton

fun TdApi.ClickVideoMessageAdvertisement.toDomain(): ClickVideoMessageAdvertisement = ClickVideoMessageAdvertisement(
    advertisementUniqueId = this.advertisementUniqueId
)

