package com.xxcactussell.data.utils.mappers.view

import com.xxcactussell.data.utils.mappers.message.toData
import com.xxcactussell.data.utils.mappers.monetization.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun ViewMessages.toData(): TdApi.ViewMessages = TdApi.ViewMessages(
    this.chatId,
    this.messageIds,
    this.source.toData(),
    this.forceRead
)

fun ViewPremiumFeature.toData(): TdApi.ViewPremiumFeature = TdApi.ViewPremiumFeature(
    this.feature.toData()
)

fun ViewSponsoredChat.toData(): TdApi.ViewSponsoredChat = TdApi.ViewSponsoredChat(
    this.sponsoredChatUniqueId
)

fun ViewTrendingStickerSets.toData(): TdApi.ViewTrendingStickerSets = TdApi.ViewTrendingStickerSets(
    this.stickerSetIds
)

fun ViewVideoMessageAdvertisement.toData(): TdApi.ViewVideoMessageAdvertisement = TdApi.ViewVideoMessageAdvertisement(
    this.advertisementUniqueId
)

