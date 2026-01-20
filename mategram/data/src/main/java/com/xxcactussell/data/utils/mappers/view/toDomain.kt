package com.xxcactussell.data.utils.mappers.view

import com.xxcactussell.data.utils.mappers.message.toDomain
import com.xxcactussell.data.utils.mappers.monetization.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.ViewMessages.toDomain(): ViewMessages = ViewMessages(
    chatId = this.chatId,
    messageIds = this.messageIds,
    source = this.source.toDomain(),
    forceRead = this.forceRead
)

fun TdApi.ViewPremiumFeature.toDomain(): ViewPremiumFeature = ViewPremiumFeature(
    feature = this.feature.toDomain()
)

fun TdApi.ViewSponsoredChat.toDomain(): ViewSponsoredChat = ViewSponsoredChat(
    sponsoredChatUniqueId = this.sponsoredChatUniqueId
)

fun TdApi.ViewTrendingStickerSets.toDomain(): ViewTrendingStickerSets = ViewTrendingStickerSets(
    stickerSetIds = this.stickerSetIds
)

fun TdApi.ViewVideoMessageAdvertisement.toDomain(): ViewVideoMessageAdvertisement = ViewVideoMessageAdvertisement(
    advertisementUniqueId = this.advertisementUniqueId
)

