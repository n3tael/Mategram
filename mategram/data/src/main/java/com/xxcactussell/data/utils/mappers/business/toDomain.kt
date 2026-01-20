package com.xxcactussell.data.utils.mappers.business

import com.xxcactussell.data.utils.mappers.animation.toDomain
import com.xxcactussell.data.utils.mappers.formatted.toDomain
import com.xxcactussell.data.utils.mappers.location.toDomain
import com.xxcactussell.data.utils.mappers.message.toDomain
import com.xxcactussell.data.utils.mappers.sticker.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.BusinessAwayMessageSchedule.toDomain(): BusinessAwayMessageSchedule = when(this) {
    is TdApi.BusinessAwayMessageScheduleAlways -> this.toDomain()
    is TdApi.BusinessAwayMessageScheduleOutsideOfOpeningHours -> this.toDomain()
    is TdApi.BusinessAwayMessageScheduleCustom -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.BusinessAwayMessageScheduleAlways.toDomain(): BusinessAwayMessageScheduleAlways = BusinessAwayMessageScheduleAlways

fun TdApi.BusinessAwayMessageScheduleCustom.toDomain(): BusinessAwayMessageScheduleCustom = BusinessAwayMessageScheduleCustom(
    startDate = this.startDate,
    endDate = this.endDate
)

fun TdApi.BusinessAwayMessageScheduleOutsideOfOpeningHours.toDomain(): BusinessAwayMessageScheduleOutsideOfOpeningHours = BusinessAwayMessageScheduleOutsideOfOpeningHours

fun TdApi.BusinessAwayMessageSettings.toDomain(): BusinessAwayMessageSettings = BusinessAwayMessageSettings(
    shortcutId = this.shortcutId,
    recipients = this.recipients.toDomain(),
    schedule = this.schedule.toDomain(),
    offlineOnly = this.offlineOnly
)

fun TdApi.BusinessBotManageBar.toDomain(): BusinessBotManageBar = BusinessBotManageBar(
    botUserId = this.botUserId,
    manageUrl = this.manageUrl,
    isBotPaused = this.isBotPaused,
    canBotReply = this.canBotReply
)

fun TdApi.BusinessBotRights.toDomain(): BusinessBotRights = BusinessBotRights(
    canReply = this.canReply,
    canReadMessages = this.canReadMessages,
    canDeleteSentMessages = this.canDeleteSentMessages,
    canDeleteAllMessages = this.canDeleteAllMessages,
    canEditName = this.canEditName,
    canEditBio = this.canEditBio,
    canEditProfilePhoto = this.canEditProfilePhoto,
    canEditUsername = this.canEditUsername,
    canViewGiftsAndStars = this.canViewGiftsAndStars,
    canSellGifts = this.canSellGifts,
    canChangeGiftSettings = this.canChangeGiftSettings,
    canTransferAndUpgradeGifts = this.canTransferAndUpgradeGifts,
    canTransferStars = this.canTransferStars,
    canManageStories = this.canManageStories
)

fun TdApi.BusinessChatLink.toDomain(): BusinessChatLink = BusinessChatLink(
    link = this.link,
    text = this.text.toDomain(),
    title = this.title,
    viewCount = this.viewCount
)

fun TdApi.BusinessChatLinkInfo.toDomain(): BusinessChatLinkInfo = BusinessChatLinkInfo(
    chatId = this.chatId,
    text = this.text.toDomain()
)

fun TdApi.BusinessChatLinks.toDomain(): BusinessChatLinks = BusinessChatLinks(
    links = this.links.map { it.toDomain() }
)

fun TdApi.BusinessConnectedBot.toDomain(): BusinessConnectedBot = BusinessConnectedBot(
    botUserId = this.botUserId,
    recipients = this.recipients.toDomain(),
    rights = this.rights.toDomain()
)

fun TdApi.BusinessFeature.toDomain(): BusinessFeature = when(this) {
    is TdApi.BusinessFeatureLocation -> this.toDomain()
    is TdApi.BusinessFeatureOpeningHours -> this.toDomain()
    is TdApi.BusinessFeatureQuickReplies -> this.toDomain()
    is TdApi.BusinessFeatureGreetingMessage -> this.toDomain()
    is TdApi.BusinessFeatureAwayMessage -> this.toDomain()
    is TdApi.BusinessFeatureAccountLinks -> this.toDomain()
    is TdApi.BusinessFeatureStartPage -> this.toDomain()
    is TdApi.BusinessFeatureBots -> this.toDomain()
    is TdApi.BusinessFeatureEmojiStatus -> this.toDomain()
    is TdApi.BusinessFeatureChatFolderTags -> this.toDomain()
    is TdApi.BusinessFeatureUpgradedStories -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.BusinessFeatureAccountLinks.toDomain(): BusinessFeatureAccountLinks = BusinessFeatureAccountLinks

fun TdApi.BusinessFeatureAwayMessage.toDomain(): BusinessFeatureAwayMessage = BusinessFeatureAwayMessage

fun TdApi.BusinessFeatureBots.toDomain(): BusinessFeatureBots = BusinessFeatureBots

fun TdApi.BusinessFeatureChatFolderTags.toDomain(): BusinessFeatureChatFolderTags = BusinessFeatureChatFolderTags

fun TdApi.BusinessFeatureEmojiStatus.toDomain(): BusinessFeatureEmojiStatus = BusinessFeatureEmojiStatus

fun TdApi.BusinessFeatureGreetingMessage.toDomain(): BusinessFeatureGreetingMessage = BusinessFeatureGreetingMessage

fun TdApi.BusinessFeatureLocation.toDomain(): BusinessFeatureLocation = BusinessFeatureLocation

fun TdApi.BusinessFeatureOpeningHours.toDomain(): BusinessFeatureOpeningHours = BusinessFeatureOpeningHours

fun TdApi.BusinessFeaturePromotionAnimation.toDomain(): BusinessFeaturePromotionAnimation = BusinessFeaturePromotionAnimation(
    feature = this.feature.toDomain(),
    animation = this.animation.toDomain()
)

fun TdApi.BusinessFeatureQuickReplies.toDomain(): BusinessFeatureQuickReplies = BusinessFeatureQuickReplies

fun TdApi.BusinessFeatureStartPage.toDomain(): BusinessFeatureStartPage = BusinessFeatureStartPage

fun TdApi.BusinessFeatureUpgradedStories.toDomain(): BusinessFeatureUpgradedStories = BusinessFeatureUpgradedStories

fun TdApi.BusinessFeatures.toDomain(): BusinessFeatures = BusinessFeatures(
    features = this.features.map { it.toDomain() }
)

fun TdApi.BusinessGreetingMessageSettings.toDomain(): BusinessGreetingMessageSettings = BusinessGreetingMessageSettings(
    shortcutId = this.shortcutId,
    recipients = this.recipients.toDomain(),
    inactivityDays = this.inactivityDays
)

fun TdApi.BusinessLocation.toDomain(): BusinessLocation = BusinessLocation(
    location = this.location?.toDomain(),
    address = this.address
)

fun TdApi.BusinessMessage.toDomain(): BusinessMessage = BusinessMessage(
    message = this.message.toDomain(),
    replyToMessage = this.replyToMessage?.toDomain()
)

fun TdApi.BusinessMessages.toDomain(): BusinessMessages = BusinessMessages(
    messages = this.messages.map { it.toDomain() }
)

fun TdApi.BusinessOpeningHours.toDomain(): BusinessOpeningHours = BusinessOpeningHours(
    timeZoneId = this.timeZoneId,
    openingHours = this.openingHours.map { it.toDomain() }
)

fun TdApi.BusinessOpeningHoursInterval.toDomain(): BusinessOpeningHoursInterval = BusinessOpeningHoursInterval(
    startMinute = this.startMinute,
    endMinute = this.endMinute
)

fun TdApi.BusinessRecipients.toDomain(): BusinessRecipients = BusinessRecipients(
    chatIds = this.chatIds,
    excludedChatIds = this.excludedChatIds,
    selectExistingChats = this.selectExistingChats,
    selectNewChats = this.selectNewChats,
    selectContacts = this.selectContacts,
    selectNonContacts = this.selectNonContacts,
    excludeSelected = this.excludeSelected
)

fun TdApi.BusinessStartPage.toDomain(): BusinessStartPage = BusinessStartPage(
    title = this.title,
    message = this.message,
    sticker = this.sticker?.toDomain()
)

