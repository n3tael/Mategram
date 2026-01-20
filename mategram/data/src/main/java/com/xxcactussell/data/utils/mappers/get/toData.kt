package com.xxcactussell.data.utils.mappers.get

import com.xxcactussell.data.utils.mappers.affiliate.toData
import com.xxcactussell.data.utils.mappers.background.toData
import com.xxcactussell.data.utils.mappers.bots.toData
import com.xxcactussell.data.utils.mappers.business.toData
import com.xxcactussell.data.utils.mappers.calls.toData
import com.xxcactussell.data.utils.mappers.collectible.toData
import com.xxcactussell.data.utils.mappers.emoji.toData
import com.xxcactussell.data.utils.mappers.formatted.toData
import com.xxcactussell.data.utils.mappers.input.toData
import com.xxcactussell.data.utils.mappers.internal.toData
import com.xxcactussell.data.utils.mappers.json.toData
import com.xxcactussell.data.utils.mappers.link.toData
import com.xxcactussell.data.utils.mappers.location.toData
import com.xxcactussell.data.utils.mappers.message.toData
import com.xxcactussell.data.utils.mappers.notification.toData
import com.xxcactussell.data.utils.mappers.public.toData
import com.xxcactussell.data.utils.mappers.sticker.toData
import com.xxcactussell.data.utils.mappers.theme.toData
import com.xxcactussell.data.utils.mappers.top.toData
import com.xxcactussell.data.utils.mappers.transaction.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun GetAllPassportElements.toData(): TdApi.GetAllPassportElements = TdApi.GetAllPassportElements(
    this.password
)

fun GetAllStickerEmojis.toData(): TdApi.GetAllStickerEmojis = TdApi.GetAllStickerEmojis(
    this.stickerType.toData(),
    this.query,
    this.chatId,
    this.returnOnlyMainEmoji
)

fun GetAnimatedEmoji.toData(): TdApi.GetAnimatedEmoji = TdApi.GetAnimatedEmoji(
    this.emoji
)

fun GetApplicationConfig.toData(): TdApi.GetApplicationConfig = TdApi.GetApplicationConfig(
)

fun GetApplicationDownloadLink.toData(): TdApi.GetApplicationDownloadLink = TdApi.GetApplicationDownloadLink(
)

fun GetArchiveChatListSettings.toData(): TdApi.GetArchiveChatListSettings = TdApi.GetArchiveChatListSettings(
)

fun GetArchivedStickerSets.toData(): TdApi.GetArchivedStickerSets = TdApi.GetArchivedStickerSets(
    this.stickerType.toData(),
    this.offsetStickerSetId,
    this.limit
)

fun GetAttachedStickerSets.toData(): TdApi.GetAttachedStickerSets = TdApi.GetAttachedStickerSets(
    this.fileId
)

fun GetAttachmentMenuBot.toData(): TdApi.GetAttachmentMenuBot = TdApi.GetAttachmentMenuBot(
    this.botUserId
)

fun GetAuthorizationState.toData(): TdApi.GetAuthorizationState = TdApi.GetAuthorizationState(
)

fun GetAutoDownloadSettingsPresets.toData(): TdApi.GetAutoDownloadSettingsPresets = TdApi.GetAutoDownloadSettingsPresets(
)

fun GetAutosaveSettings.toData(): TdApi.GetAutosaveSettings = TdApi.GetAutosaveSettings(
)

fun GetAvailableChatBoostSlots.toData(): TdApi.GetAvailableChatBoostSlots = TdApi.GetAvailableChatBoostSlots(
)

fun GetBackgroundUrl.toData(): TdApi.GetBackgroundUrl = TdApi.GetBackgroundUrl(
    this.name,
    this.type.toData()
)

fun GetBankCardInfo.toData(): TdApi.GetBankCardInfo = TdApi.GetBankCardInfo(
    this.bankCardNumber
)

fun GetBotInfoDescription.toData(): TdApi.GetBotInfoDescription = TdApi.GetBotInfoDescription(
    this.botUserId,
    this.languageCode
)

fun GetBotInfoShortDescription.toData(): TdApi.GetBotInfoShortDescription = TdApi.GetBotInfoShortDescription(
    this.botUserId,
    this.languageCode
)

fun GetBotMediaPreviewInfo.toData(): TdApi.GetBotMediaPreviewInfo = TdApi.GetBotMediaPreviewInfo(
    this.botUserId,
    this.languageCode
)

fun GetBotMediaPreviews.toData(): TdApi.GetBotMediaPreviews = TdApi.GetBotMediaPreviews(
    this.botUserId
)

fun GetBotSimilarBotCount.toData(): TdApi.GetBotSimilarBotCount = TdApi.GetBotSimilarBotCount(
    this.botUserId,
    this.returnLocal
)

fun GetBotSimilarBots.toData(): TdApi.GetBotSimilarBots = TdApi.GetBotSimilarBots(
    this.botUserId
)

fun GetBusinessAccountStarAmount.toData(): TdApi.GetBusinessAccountStarAmount = TdApi.GetBusinessAccountStarAmount(
    this.businessConnectionId
)

fun GetBusinessChatLinkInfo.toData(): TdApi.GetBusinessChatLinkInfo = TdApi.GetBusinessChatLinkInfo(
    this.linkName
)

fun GetBusinessChatLinks.toData(): TdApi.GetBusinessChatLinks = TdApi.GetBusinessChatLinks(
)

fun GetBusinessConnectedBot.toData(): TdApi.GetBusinessConnectedBot = TdApi.GetBusinessConnectedBot(
)

fun GetBusinessFeatures.toData(): TdApi.GetBusinessFeatures = TdApi.GetBusinessFeatures(
    this.source.toData()
)

fun GetCallbackQueryAnswer.toData(): TdApi.GetCallbackQueryAnswer = TdApi.GetCallbackQueryAnswer(
    this.chatId,
    this.messageId,
    this.payload.toData()
)

fun GetCallbackQueryMessage.toData(): TdApi.GetCallbackQueryMessage = TdApi.GetCallbackQueryMessage(
    this.chatId,
    this.messageId,
    this.callbackQueryId
)

fun GetCloseFriends.toData(): TdApi.GetCloseFriends = TdApi.GetCloseFriends(
)

fun GetCollectibleItemInfo.toData(): TdApi.GetCollectibleItemInfo = TdApi.GetCollectibleItemInfo(
    this.type.toData()
)

fun GetCommands.toData(): TdApi.GetCommands = TdApi.GetCommands(
    this.scope.toData(),
    this.languageCode
)

fun GetConnectedAffiliateProgram.toData(): TdApi.GetConnectedAffiliateProgram = TdApi.GetConnectedAffiliateProgram(
    this.affiliate.toData(),
    this.botUserId
)

fun GetConnectedAffiliatePrograms.toData(): TdApi.GetConnectedAffiliatePrograms = TdApi.GetConnectedAffiliatePrograms(
    this.affiliate.toData(),
    this.offset,
    this.limit
)

fun GetCountries.toData(): TdApi.GetCountries = TdApi.GetCountries(
)

fun GetCountryCode.toData(): TdApi.GetCountryCode = TdApi.GetCountryCode(
)

fun GetCountryFlagEmoji.toData(): TdApi.GetCountryFlagEmoji = TdApi.GetCountryFlagEmoji(
    this.countryCode
)

fun GetCreatedPublicChats.toData(): TdApi.GetCreatedPublicChats = TdApi.GetCreatedPublicChats(
    this.type.toData()
)

fun GetCurrentState.toData(): TdApi.GetCurrentState = TdApi.GetCurrentState(
)

fun GetCurrentWeather.toData(): TdApi.GetCurrentWeather = TdApi.GetCurrentWeather(
    this.location.toData()
)

fun GetCustomEmojiReactionAnimations.toData(): TdApi.GetCustomEmojiReactionAnimations = TdApi.GetCustomEmojiReactionAnimations(
)

fun GetCustomEmojiStickers.toData(): TdApi.GetCustomEmojiStickers = TdApi.GetCustomEmojiStickers(
    this.customEmojiIds
)

fun GetDatabaseStatistics.toData(): TdApi.GetDatabaseStatistics = TdApi.GetDatabaseStatistics(
)

fun GetDeepLinkInfo.toData(): TdApi.GetDeepLinkInfo = TdApi.GetDeepLinkInfo(
    this.link
)

fun GetDefaultBackgroundCustomEmojiStickers.toData(): TdApi.GetDefaultBackgroundCustomEmojiStickers = TdApi.GetDefaultBackgroundCustomEmojiStickers(
)

fun GetDefaultChatEmojiStatuses.toData(): TdApi.GetDefaultChatEmojiStatuses = TdApi.GetDefaultChatEmojiStatuses(
)

fun GetDefaultChatPhotoCustomEmojiStickers.toData(): TdApi.GetDefaultChatPhotoCustomEmojiStickers = TdApi.GetDefaultChatPhotoCustomEmojiStickers(
)

fun GetDefaultEmojiStatuses.toData(): TdApi.GetDefaultEmojiStatuses = TdApi.GetDefaultEmojiStatuses(
)

fun GetDefaultMessageAutoDeleteTime.toData(): TdApi.GetDefaultMessageAutoDeleteTime = TdApi.GetDefaultMessageAutoDeleteTime(
)

fun GetDefaultProfilePhotoCustomEmojiStickers.toData(): TdApi.GetDefaultProfilePhotoCustomEmojiStickers = TdApi.GetDefaultProfilePhotoCustomEmojiStickers(
)

fun GetDirectMessagesChatTopic.toData(): TdApi.GetDirectMessagesChatTopic = TdApi.GetDirectMessagesChatTopic(
    this.chatId,
    this.topicId
)

fun GetDirectMessagesChatTopicHistory.toData(): TdApi.GetDirectMessagesChatTopicHistory = TdApi.GetDirectMessagesChatTopicHistory(
    this.chatId,
    this.topicId,
    this.fromMessageId,
    this.offset,
    this.limit
)

fun GetDirectMessagesChatTopicMessageByDate.toData(): TdApi.GetDirectMessagesChatTopicMessageByDate = TdApi.GetDirectMessagesChatTopicMessageByDate(
    this.chatId,
    this.topicId,
    this.date
)

fun GetDirectMessagesChatTopicRevenue.toData(): TdApi.GetDirectMessagesChatTopicRevenue = TdApi.GetDirectMessagesChatTopicRevenue(
    this.chatId,
    this.topicId
)

fun GetDisallowedChatEmojiStatuses.toData(): TdApi.GetDisallowedChatEmojiStatuses = TdApi.GetDisallowedChatEmojiStatuses(
)

fun GetEmojiCategories.toData(): TdApi.GetEmojiCategories = TdApi.GetEmojiCategories(
    this.type.toData()
)

fun GetEmojiReaction.toData(): TdApi.GetEmojiReaction = TdApi.GetEmojiReaction(
    this.emoji
)

fun GetEmojiSuggestionsUrl.toData(): TdApi.GetEmojiSuggestionsUrl = TdApi.GetEmojiSuggestionsUrl(
    this.languageCode
)

fun GetExternalLink.toData(): TdApi.GetExternalLink = TdApi.GetExternalLink(
    this.link,
    this.allowWriteAccess
)

fun GetExternalLinkInfo.toData(): TdApi.GetExternalLinkInfo = TdApi.GetExternalLinkInfo(
    this.link
)

fun GetFavoriteStickers.toData(): TdApi.GetFavoriteStickers = TdApi.GetFavoriteStickers(
)

fun GetGameHighScores.toData(): TdApi.GetGameHighScores = TdApi.GetGameHighScores(
    this.chatId,
    this.messageId,
    this.userId
)

fun GetGiftChatThemes.toData(): TdApi.GetGiftChatThemes = TdApi.GetGiftChatThemes(
    this.offset,
    this.limit
)

fun GetGiftCollections.toData(): TdApi.GetGiftCollections = TdApi.GetGiftCollections(
    this.ownerId.toData()
)

fun GetGiftUpgradePreview.toData(): TdApi.GetGiftUpgradePreview = TdApi.GetGiftUpgradePreview(
    this.giftId
)

fun GetGiveawayInfo.toData(): TdApi.GetGiveawayInfo = TdApi.GetGiveawayInfo(
    this.chatId,
    this.messageId
)

fun GetGreetingStickers.toData(): TdApi.GetGreetingStickers = TdApi.GetGreetingStickers(
)

fun GetGrossingWebAppBots.toData(): TdApi.GetGrossingWebAppBots = TdApi.GetGrossingWebAppBots(
    this.offset,
    this.limit
)

fun GetGroupsInCommon.toData(): TdApi.GetGroupsInCommon = TdApi.GetGroupsInCommon(
    this.userId,
    this.offsetChatId,
    this.limit
)

fun GetImportedContactCount.toData(): TdApi.GetImportedContactCount = TdApi.GetImportedContactCount(
)

fun GetInactiveSupergroupChats.toData(): TdApi.GetInactiveSupergroupChats = TdApi.GetInactiveSupergroupChats(
)

fun GetInlineGameHighScores.toData(): TdApi.GetInlineGameHighScores = TdApi.GetInlineGameHighScores(
    this.inlineMessageId,
    this.userId
)

fun GetInlineQueryResults.toData(): TdApi.GetInlineQueryResults = TdApi.GetInlineQueryResults(
    this.botUserId,
    this.chatId,
    this.userLocation.toData(),
    this.query,
    this.offset
)

fun GetInstalledBackgrounds.toData(): TdApi.GetInstalledBackgrounds = TdApi.GetInstalledBackgrounds(
    this.forDarkTheme
)

fun GetInstalledStickerSets.toData(): TdApi.GetInstalledStickerSets = TdApi.GetInstalledStickerSets(
    this.stickerType.toData()
)

fun GetInternalLink.toData(): TdApi.GetInternalLink = TdApi.GetInternalLink(
    this.type.toData(),
    this.isHttp
)

fun GetInternalLinkType.toData(): TdApi.GetInternalLinkType = TdApi.GetInternalLinkType(
    this.link
)

fun GetJsonString.toData(): TdApi.GetJsonString = TdApi.GetJsonString(
    this.jsonValue.toData()
)

fun GetJsonValue.toData(): TdApi.GetJsonValue = TdApi.GetJsonValue(
    this.json
)

fun GetKeywordEmojis.toData(): TdApi.GetKeywordEmojis = TdApi.GetKeywordEmojis(
    this.text,
    this.inputLanguageCodes.toTypedArray()
)

fun GetLanguagePackInfo.toData(): TdApi.GetLanguagePackInfo = TdApi.GetLanguagePackInfo(
    this.languagePackId
)

fun GetLanguagePackString.toData(): TdApi.GetLanguagePackString = TdApi.GetLanguagePackString(
    this.languagePackDatabasePath,
    this.localizationTarget,
    this.languagePackId,
    this.key
)

fun GetLanguagePackStrings.toData(): TdApi.GetLanguagePackStrings = TdApi.GetLanguagePackStrings(
    this.languagePackId,
    this.keys.toTypedArray()
)

fun GetLinkPreview.toData(): TdApi.GetLinkPreview = TdApi.GetLinkPreview(
    this.text.toData(),
    this.linkPreviewOptions.toData()
)

fun GetLocalizationTargetInfo.toData(): TdApi.GetLocalizationTargetInfo = TdApi.GetLocalizationTargetInfo(
    this.onlyLocal
)

fun GetLogTagVerbosityLevel.toData(): TdApi.GetLogTagVerbosityLevel = TdApi.GetLogTagVerbosityLevel(
    this.tag
)

fun GetLoginUrl.toData(): TdApi.GetLoginUrl = TdApi.GetLoginUrl(
    this.chatId,
    this.messageId,
    this.buttonId,
    this.allowWriteAccess
)

fun GetLoginUrlInfo.toData(): TdApi.GetLoginUrlInfo = TdApi.GetLoginUrlInfo(
    this.chatId,
    this.messageId,
    this.buttonId
)

fun GetMainWebApp.toData(): TdApi.GetMainWebApp = TdApi.GetMainWebApp(
    this.chatId,
    this.botUserId,
    this.startParameter,
    this.parameters.toData()
)

fun GetMapThumbnailFile.toData(): TdApi.GetMapThumbnailFile = TdApi.GetMapThumbnailFile(
    this.location.toData(),
    this.zoom,
    this.width,
    this.height,
    this.scale,
    this.chatId
)

fun GetMarkdownText.toData(): TdApi.GetMarkdownText = TdApi.GetMarkdownText(
    this.text.toData()
)

fun GetNewChatPrivacySettings.toData(): TdApi.GetNewChatPrivacySettings = TdApi.GetNewChatPrivacySettings(
)

fun GetOwnedBots.toData(): TdApi.GetOwnedBots = TdApi.GetOwnedBots(
)

fun GetOwnedStickerSets.toData(): TdApi.GetOwnedStickerSets = TdApi.GetOwnedStickerSets(
    this.offsetStickerSetId,
    this.limit
)

fun GetPaidMessageRevenue.toData(): TdApi.GetPaidMessageRevenue = TdApi.GetPaidMessageRevenue(
    this.userId
)

fun GetPasswordState.toData(): TdApi.GetPasswordState = TdApi.GetPasswordState(
)

fun GetPhoneNumberInfo.toData(): TdApi.GetPhoneNumberInfo = TdApi.GetPhoneNumberInfo(
    this.phoneNumberPrefix
)

fun GetPhoneNumberInfoSync.toData(): TdApi.GetPhoneNumberInfoSync = TdApi.GetPhoneNumberInfoSync(
    this.languageCode,
    this.phoneNumberPrefix
)

fun GetPollVoters.toData(): TdApi.GetPollVoters = TdApi.GetPollVoters(
    this.chatId,
    this.messageId,
    this.optionId,
    this.offset,
    this.limit
)

fun GetPreferredCountryLanguage.toData(): TdApi.GetPreferredCountryLanguage = TdApi.GetPreferredCountryLanguage(
    this.countryCode
)

fun GetPremiumGiftPaymentOptions.toData(): TdApi.GetPremiumGiftPaymentOptions = TdApi.GetPremiumGiftPaymentOptions(
)

fun GetPremiumGiveawayPaymentOptions.toData(): TdApi.GetPremiumGiveawayPaymentOptions = TdApi.GetPremiumGiveawayPaymentOptions(
    this.boostedChatId
)

fun GetPremiumInfoSticker.toData(): TdApi.GetPremiumInfoSticker = TdApi.GetPremiumInfoSticker(
    this.monthCount
)

fun GetPremiumStickerExamples.toData(): TdApi.GetPremiumStickerExamples = TdApi.GetPremiumStickerExamples(
)

fun GetPremiumStickers.toData(): TdApi.GetPremiumStickers = TdApi.GetPremiumStickers(
    this.limit
)

fun GetPreparedInlineMessage.toData(): TdApi.GetPreparedInlineMessage = TdApi.GetPreparedInlineMessage(
    this.botUserId,
    this.preparedMessageId
)

fun GetPublicPostSearchLimits.toData(): TdApi.GetPublicPostSearchLimits = TdApi.GetPublicPostSearchLimits(
    this.query
)

fun GetPushReceiverId.toData(): TdApi.GetPushReceiverId = TdApi.GetPushReceiverId(
    this.payload
)

fun GetReadDatePrivacySettings.toData(): TdApi.GetReadDatePrivacySettings = TdApi.GetReadDatePrivacySettings(
)

fun GetReceivedGift.toData(): TdApi.GetReceivedGift = TdApi.GetReceivedGift(
    this.receivedGiftId
)

fun GetReceivedGifts.toData(): TdApi.GetReceivedGifts = TdApi.GetReceivedGifts(
    this.businessConnectionId,
    this.ownerId.toData(),
    this.collectionId,
    this.excludeUnsaved,
    this.excludeSaved,
    this.excludeUnlimited,
    this.excludeUpgradable,
    this.excludeNonUpgradable,
    this.excludeUpgraded,
    this.sortByPrice,
    this.offset,
    this.limit
)

fun GetRecentEmojiStatuses.toData(): TdApi.GetRecentEmojiStatuses = TdApi.GetRecentEmojiStatuses(
)

fun GetRecentInlineBots.toData(): TdApi.GetRecentInlineBots = TdApi.GetRecentInlineBots(
)

fun GetRecentStickers.toData(): TdApi.GetRecentStickers = TdApi.GetRecentStickers(
    this.isAttached
)

fun GetRecentlyOpenedChats.toData(): TdApi.GetRecentlyOpenedChats = TdApi.GetRecentlyOpenedChats(
    this.limit
)

fun GetRecentlyVisitedTMeUrls.toData(): TdApi.GetRecentlyVisitedTMeUrls = TdApi.GetRecentlyVisitedTMeUrls(
    this.referrer
)

fun GetRecommendedChatFolders.toData(): TdApi.GetRecommendedChatFolders = TdApi.GetRecommendedChatFolders(
)

fun GetRecommendedChats.toData(): TdApi.GetRecommendedChats = TdApi.GetRecommendedChats(
)

fun GetRecoveryEmailAddress.toData(): TdApi.GetRecoveryEmailAddress = TdApi.GetRecoveryEmailAddress(
    this.password
)

fun GetRepliedMessage.toData(): TdApi.GetRepliedMessage = TdApi.GetRepliedMessage(
    this.chatId,
    this.messageId
)

fun GetSavedAnimations.toData(): TdApi.GetSavedAnimations = TdApi.GetSavedAnimations(
)

fun GetSavedMessagesTags.toData(): TdApi.GetSavedMessagesTags = TdApi.GetSavedMessagesTags(
    this.savedMessagesTopicId
)

fun GetSavedMessagesTopicHistory.toData(): TdApi.GetSavedMessagesTopicHistory = TdApi.GetSavedMessagesTopicHistory(
    this.savedMessagesTopicId,
    this.fromMessageId,
    this.offset,
    this.limit
)

fun GetSavedMessagesTopicMessageByDate.toData(): TdApi.GetSavedMessagesTopicMessageByDate = TdApi.GetSavedMessagesTopicMessageByDate(
    this.savedMessagesTopicId,
    this.date
)

fun GetSavedNotificationSound.toData(): TdApi.GetSavedNotificationSound = TdApi.GetSavedNotificationSound(
    this.notificationSoundId
)

fun GetSavedNotificationSounds.toData(): TdApi.GetSavedNotificationSounds = TdApi.GetSavedNotificationSounds(
)

fun GetScopeNotificationSettings.toData(): TdApi.GetScopeNotificationSettings = TdApi.GetScopeNotificationSettings(
    this.scope.toData()
)

fun GetSearchSponsoredChats.toData(): TdApi.GetSearchSponsoredChats = TdApi.GetSearchSponsoredChats(
    this.query
)

fun GetSearchedForTags.toData(): TdApi.GetSearchedForTags = TdApi.GetSearchedForTags(
    this.tagPrefix,
    this.limit
)

fun GetSecretChat.toData(): TdApi.GetSecretChat = TdApi.GetSecretChat(
    this.secretChatId
)

fun GetStarAdAccountUrl.toData(): TdApi.GetStarAdAccountUrl = TdApi.GetStarAdAccountUrl(
    this.ownerId.toData()
)

fun GetStarGiftPaymentOptions.toData(): TdApi.GetStarGiftPaymentOptions = TdApi.GetStarGiftPaymentOptions(
    this.userId
)

fun GetStarGiveawayPaymentOptions.toData(): TdApi.GetStarGiveawayPaymentOptions = TdApi.GetStarGiveawayPaymentOptions(
)

fun GetStarPaymentOptions.toData(): TdApi.GetStarPaymentOptions = TdApi.GetStarPaymentOptions(
)

fun GetStarSubscriptions.toData(): TdApi.GetStarSubscriptions = TdApi.GetStarSubscriptions(
    this.onlyExpiring,
    this.offset
)

fun GetStarTransactions.toData(): TdApi.GetStarTransactions = TdApi.GetStarTransactions(
    this.ownerId.toData(),
    this.subscriptionId,
    this.direction.toData(),
    this.offset,
    this.limit
)

fun GetStatisticalGraph.toData(): TdApi.GetStatisticalGraph = TdApi.GetStatisticalGraph(
    this.chatId,
    this.token,
    this.x
)

fun GetStickerEmojis.toData(): TdApi.GetStickerEmojis = TdApi.GetStickerEmojis(
    this.sticker.toData()
)

fun GetStickerOutline.toData(): TdApi.GetStickerOutline = TdApi.GetStickerOutline(
    this.stickerFileId,
    this.forAnimatedEmoji,
    this.forClickedAnimatedEmojiMessage
)

fun GetStickerSet.toData(): TdApi.GetStickerSet = TdApi.GetStickerSet(
    this.setId
)

fun GetStickerSetName.toData(): TdApi.GetStickerSetName = TdApi.GetStickerSetName(
    this.setId
)

fun GetStickers.toData(): TdApi.GetStickers = TdApi.GetStickers(
    this.stickerType.toData(),
    this.query,
    this.limit,
    this.chatId
)

fun GetStorageStatistics.toData(): TdApi.GetStorageStatistics = TdApi.GetStorageStatistics(
    this.chatLimit
)

fun GetStorageStatisticsFast.toData(): TdApi.GetStorageStatisticsFast = TdApi.GetStorageStatisticsFast(
)

fun GetSuggestedFileName.toData(): TdApi.GetSuggestedFileName = TdApi.GetSuggestedFileName(
    this.fileId,
    this.directory
)

fun GetSuggestedStickerSetName.toData(): TdApi.GetSuggestedStickerSetName = TdApi.GetSuggestedStickerSetName(
    this.title
)

fun GetSuitableDiscussionChats.toData(): TdApi.GetSuitableDiscussionChats = TdApi.GetSuitableDiscussionChats(
)

fun GetSuitablePersonalChats.toData(): TdApi.GetSuitablePersonalChats = TdApi.GetSuitablePersonalChats(
)

fun GetSupportName.toData(): TdApi.GetSupportName = TdApi.GetSupportName(
)

fun GetSupportUser.toData(): TdApi.GetSupportUser = TdApi.GetSupportUser(
)

fun GetTemporaryPasswordState.toData(): TdApi.GetTemporaryPasswordState = TdApi.GetTemporaryPasswordState(
)

fun GetTextEntities.toData(): TdApi.GetTextEntities = TdApi.GetTextEntities(
    this.text
)

fun GetThemeParametersJsonString.toData(): TdApi.GetThemeParametersJsonString = TdApi.GetThemeParametersJsonString(
    this.theme.toData()
)

fun GetThemedChatEmojiStatuses.toData(): TdApi.GetThemedChatEmojiStatuses = TdApi.GetThemedChatEmojiStatuses(
)

fun GetThemedEmojiStatuses.toData(): TdApi.GetThemedEmojiStatuses = TdApi.GetThemedEmojiStatuses(
)

fun GetTimeZones.toData(): TdApi.GetTimeZones = TdApi.GetTimeZones(
)

fun GetTonRevenueStatistics.toData(): TdApi.GetTonRevenueStatistics = TdApi.GetTonRevenueStatistics(
    this.isDark
)

fun GetTonTransactions.toData(): TdApi.GetTonTransactions = TdApi.GetTonTransactions(
    this.direction.toData(),
    this.offset,
    this.limit
)

fun GetTonWithdrawalUrl.toData(): TdApi.GetTonWithdrawalUrl = TdApi.GetTonWithdrawalUrl(
    this.password
)

fun GetTopChats.toData(): TdApi.GetTopChats = TdApi.GetTopChats(
    this.category.toData(),
    this.limit
)

fun GetTrendingStickerSets.toData(): TdApi.GetTrendingStickerSets = TdApi.GetTrendingStickerSets(
    this.stickerType.toData(),
    this.offset,
    this.limit
)

fun GetUpgradedGift.toData(): TdApi.GetUpgradedGift = TdApi.GetUpgradedGift(
    this.name
)

fun GetUpgradedGiftEmojiStatuses.toData(): TdApi.GetUpgradedGiftEmojiStatuses = TdApi.GetUpgradedGiftEmojiStatuses(
)

fun GetUpgradedGiftValueInfo.toData(): TdApi.GetUpgradedGiftValueInfo = TdApi.GetUpgradedGiftValueInfo(
    this.name
)

fun GetUpgradedGiftWithdrawalUrl.toData(): TdApi.GetUpgradedGiftWithdrawalUrl = TdApi.GetUpgradedGiftWithdrawalUrl(
    this.receivedGiftId,
    this.password
)

fun GetVideoChatAvailableParticipants.toData(): TdApi.GetVideoChatAvailableParticipants = TdApi.GetVideoChatAvailableParticipants(
    this.chatId
)

fun GetVideoChatInviteLink.toData(): TdApi.GetVideoChatInviteLink = TdApi.GetVideoChatInviteLink(
    this.groupCallId,
    this.canSelfUnmute
)

fun GetVideoChatRtmpUrl.toData(): TdApi.GetVideoChatRtmpUrl = TdApi.GetVideoChatRtmpUrl(
    this.chatId
)

fun GetVideoChatStreamSegment.toData(): TdApi.GetVideoChatStreamSegment = TdApi.GetVideoChatStreamSegment(
    this.groupCallId,
    this.timeOffset,
    this.scale,
    this.channelId,
    this.videoQuality.toData()
)

fun GetVideoChatStreams.toData(): TdApi.GetVideoChatStreams = TdApi.GetVideoChatStreams(
    this.groupCallId
)

fun GetVideoMessageAdvertisements.toData(): TdApi.GetVideoMessageAdvertisements = TdApi.GetVideoMessageAdvertisements(
    this.chatId,
    this.messageId
)

fun GetWebAppLinkUrl.toData(): TdApi.GetWebAppLinkUrl = TdApi.GetWebAppLinkUrl(
    this.chatId,
    this.botUserId,
    this.webAppShortName,
    this.startParameter,
    this.allowWriteAccess,
    this.parameters.toData()
)

fun GetWebAppPlaceholder.toData(): TdApi.GetWebAppPlaceholder = TdApi.GetWebAppPlaceholder(
    this.botUserId
)

fun GetWebPageInstantView.toData(): TdApi.GetWebPageInstantView = TdApi.GetWebPageInstantView(
    this.url,
    this.onlyLocal
)

