package com.xxcactussell.data.utils.mappers.business

import com.xxcactussell.data.utils.mappers.animation.toData
import com.xxcactussell.data.utils.mappers.formatted.toData
import com.xxcactussell.data.utils.mappers.location.toData
import com.xxcactussell.data.utils.mappers.message.toData
import com.xxcactussell.data.utils.mappers.sticker.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun BusinessAwayMessageSchedule.toData(): TdApi.BusinessAwayMessageSchedule = when(this) {
    is BusinessAwayMessageScheduleAlways -> this.toData()
    is BusinessAwayMessageScheduleOutsideOfOpeningHours -> this.toData()
    is BusinessAwayMessageScheduleCustom -> this.toData()
}

fun BusinessAwayMessageScheduleAlways.toData(): TdApi.BusinessAwayMessageScheduleAlways = TdApi.BusinessAwayMessageScheduleAlways(
)

fun BusinessAwayMessageScheduleCustom.toData(): TdApi.BusinessAwayMessageScheduleCustom = TdApi.BusinessAwayMessageScheduleCustom(
    this.startDate,
    this.endDate
)

fun BusinessAwayMessageScheduleOutsideOfOpeningHours.toData(): TdApi.BusinessAwayMessageScheduleOutsideOfOpeningHours = TdApi.BusinessAwayMessageScheduleOutsideOfOpeningHours(
)

fun BusinessAwayMessageSettings.toData(): TdApi.BusinessAwayMessageSettings = TdApi.BusinessAwayMessageSettings(
    this.shortcutId,
    this.recipients.toData(),
    this.schedule.toData(),
    this.offlineOnly
)

fun BusinessBotManageBar.toData(): TdApi.BusinessBotManageBar = TdApi.BusinessBotManageBar(
    this.botUserId,
    this.manageUrl,
    this.isBotPaused,
    this.canBotReply
)

fun BusinessBotRights.toData(): TdApi.BusinessBotRights = TdApi.BusinessBotRights(
    this.canReply,
    this.canReadMessages,
    this.canDeleteSentMessages,
    this.canDeleteAllMessages,
    this.canEditName,
    this.canEditBio,
    this.canEditProfilePhoto,
    this.canEditUsername,
    this.canViewGiftsAndStars,
    this.canSellGifts,
    this.canChangeGiftSettings,
    this.canTransferAndUpgradeGifts,
    this.canTransferStars,
    this.canManageStories
)

fun BusinessChatLink.toData(): TdApi.BusinessChatLink = TdApi.BusinessChatLink(
    this.link,
    this.text.toData(),
    this.title,
    this.viewCount
)

fun BusinessChatLinkInfo.toData(): TdApi.BusinessChatLinkInfo = TdApi.BusinessChatLinkInfo(
    this.chatId,
    this.text.toData()
)

fun BusinessChatLinks.toData(): TdApi.BusinessChatLinks = TdApi.BusinessChatLinks(
    this.links.map { it.toData() }.toTypedArray()
)

fun BusinessConnectedBot.toData(): TdApi.BusinessConnectedBot = TdApi.BusinessConnectedBot(
    this.botUserId,
    this.recipients.toData(),
    this.rights.toData()
)

fun BusinessFeature.toData(): TdApi.BusinessFeature = when(this) {
    is BusinessFeatureLocation -> this.toData()
    is BusinessFeatureOpeningHours -> this.toData()
    is BusinessFeatureQuickReplies -> this.toData()
    is BusinessFeatureGreetingMessage -> this.toData()
    is BusinessFeatureAwayMessage -> this.toData()
    is BusinessFeatureAccountLinks -> this.toData()
    is BusinessFeatureStartPage -> this.toData()
    is BusinessFeatureBots -> this.toData()
    is BusinessFeatureEmojiStatus -> this.toData()
    is BusinessFeatureChatFolderTags -> this.toData()
    is BusinessFeatureUpgradedStories -> this.toData()
}

fun BusinessFeatureAccountLinks.toData(): TdApi.BusinessFeatureAccountLinks = TdApi.BusinessFeatureAccountLinks(
)

fun BusinessFeatureAwayMessage.toData(): TdApi.BusinessFeatureAwayMessage = TdApi.BusinessFeatureAwayMessage(
)

fun BusinessFeatureBots.toData(): TdApi.BusinessFeatureBots = TdApi.BusinessFeatureBots(
)

fun BusinessFeatureChatFolderTags.toData(): TdApi.BusinessFeatureChatFolderTags = TdApi.BusinessFeatureChatFolderTags(
)

fun BusinessFeatureEmojiStatus.toData(): TdApi.BusinessFeatureEmojiStatus = TdApi.BusinessFeatureEmojiStatus(
)

fun BusinessFeatureGreetingMessage.toData(): TdApi.BusinessFeatureGreetingMessage = TdApi.BusinessFeatureGreetingMessage(
)

fun BusinessFeatureLocation.toData(): TdApi.BusinessFeatureLocation = TdApi.BusinessFeatureLocation(
)

fun BusinessFeatureOpeningHours.toData(): TdApi.BusinessFeatureOpeningHours = TdApi.BusinessFeatureOpeningHours(
)

fun BusinessFeaturePromotionAnimation.toData(): TdApi.BusinessFeaturePromotionAnimation = TdApi.BusinessFeaturePromotionAnimation(
    this.feature.toData(),
    this.animation.toData()
)

fun BusinessFeatureQuickReplies.toData(): TdApi.BusinessFeatureQuickReplies = TdApi.BusinessFeatureQuickReplies(
)

fun BusinessFeatureStartPage.toData(): TdApi.BusinessFeatureStartPage = TdApi.BusinessFeatureStartPage(
)

fun BusinessFeatureUpgradedStories.toData(): TdApi.BusinessFeatureUpgradedStories = TdApi.BusinessFeatureUpgradedStories(
)

fun BusinessFeatures.toData(): TdApi.BusinessFeatures = TdApi.BusinessFeatures(
    this.features.map { it.toData() }.toTypedArray()
)

fun BusinessGreetingMessageSettings.toData(): TdApi.BusinessGreetingMessageSettings = TdApi.BusinessGreetingMessageSettings(
    this.shortcutId,
    this.recipients.toData(),
    this.inactivityDays
)

fun BusinessLocation.toData(): TdApi.BusinessLocation = TdApi.BusinessLocation(
    this.location?.toData(),
    this.address
)

fun BusinessMessage.toData(): TdApi.BusinessMessage = TdApi.BusinessMessage(
    this.message.toData(),
    this.replyToMessage?.toData()
)

fun BusinessMessages.toData(): TdApi.BusinessMessages = TdApi.BusinessMessages(
    this.messages.map { it.toData() }.toTypedArray()
)

fun BusinessOpeningHours.toData(): TdApi.BusinessOpeningHours = TdApi.BusinessOpeningHours(
    this.timeZoneId,
    this.openingHours.map { it.toData() }.toTypedArray()
)

fun BusinessOpeningHoursInterval.toData(): TdApi.BusinessOpeningHoursInterval = TdApi.BusinessOpeningHoursInterval(
    this.startMinute,
    this.endMinute
)

fun BusinessRecipients.toData(): TdApi.BusinessRecipients = TdApi.BusinessRecipients(
    this.chatIds,
    this.excludedChatIds,
    this.selectExistingChats,
    this.selectNewChats,
    this.selectContacts,
    this.selectNonContacts,
    this.excludeSelected
)

fun BusinessStartPage.toData(): TdApi.BusinessStartPage = TdApi.BusinessStartPage(
    this.title,
    this.message,
    this.sticker?.toData()
)

