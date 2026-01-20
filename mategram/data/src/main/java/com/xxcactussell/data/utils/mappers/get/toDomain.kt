package com.xxcactussell.data.utils.mappers.get

import com.xxcactussell.data.utils.mappers.affiliate.toDomain
import com.xxcactussell.data.utils.mappers.background.toDomain
import com.xxcactussell.data.utils.mappers.bots.toDomain
import com.xxcactussell.data.utils.mappers.business.toDomain
import com.xxcactussell.data.utils.mappers.calls.toDomain
import com.xxcactussell.data.utils.mappers.collectible.toDomain
import com.xxcactussell.data.utils.mappers.emoji.toDomain
import com.xxcactussell.data.utils.mappers.formatted.toDomain
import com.xxcactussell.data.utils.mappers.input.toDomain
import com.xxcactussell.data.utils.mappers.internal.toDomain
import com.xxcactussell.data.utils.mappers.json.toDomain
import com.xxcactussell.data.utils.mappers.link.toDomain
import com.xxcactussell.data.utils.mappers.location.toDomain
import com.xxcactussell.data.utils.mappers.message.toDomain
import com.xxcactussell.data.utils.mappers.notification.toDomain
import com.xxcactussell.data.utils.mappers.public.toDomain
import com.xxcactussell.data.utils.mappers.sticker.toDomain
import com.xxcactussell.data.utils.mappers.theme.toDomain
import com.xxcactussell.data.utils.mappers.top.toDomain
import com.xxcactussell.data.utils.mappers.transaction.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.GetAllPassportElements.toDomain(): GetAllPassportElements = GetAllPassportElements(
    password = this.password
)

fun TdApi.GetAllStickerEmojis.toDomain(): GetAllStickerEmojis = GetAllStickerEmojis(
    stickerType = this.stickerType.toDomain(),
    query = this.query,
    chatId = this.chatId,
    returnOnlyMainEmoji = this.returnOnlyMainEmoji
)

fun TdApi.GetAnimatedEmoji.toDomain(): GetAnimatedEmoji = GetAnimatedEmoji(
    emoji = this.emoji
)

fun TdApi.GetApplicationConfig.toDomain(): GetApplicationConfig = GetApplicationConfig

fun TdApi.GetApplicationDownloadLink.toDomain(): GetApplicationDownloadLink = GetApplicationDownloadLink

fun TdApi.GetArchiveChatListSettings.toDomain(): GetArchiveChatListSettings = GetArchiveChatListSettings

fun TdApi.GetArchivedStickerSets.toDomain(): GetArchivedStickerSets = GetArchivedStickerSets(
    stickerType = this.stickerType.toDomain(),
    offsetStickerSetId = this.offsetStickerSetId,
    limit = this.limit
)

fun TdApi.GetAttachedStickerSets.toDomain(): GetAttachedStickerSets = GetAttachedStickerSets(
    fileId = this.fileId
)

fun TdApi.GetAttachmentMenuBot.toDomain(): GetAttachmentMenuBot = GetAttachmentMenuBot(
    botUserId = this.botUserId
)

fun TdApi.GetAuthorizationState.toDomain(): GetAuthorizationState = GetAuthorizationState

fun TdApi.GetAutoDownloadSettingsPresets.toDomain(): GetAutoDownloadSettingsPresets = GetAutoDownloadSettingsPresets

fun TdApi.GetAutosaveSettings.toDomain(): GetAutosaveSettings = GetAutosaveSettings

fun TdApi.GetAvailableChatBoostSlots.toDomain(): GetAvailableChatBoostSlots = GetAvailableChatBoostSlots

fun TdApi.GetBackgroundUrl.toDomain(): GetBackgroundUrl = GetBackgroundUrl(
    name = this.name,
    type = this.type.toDomain()
)

fun TdApi.GetBankCardInfo.toDomain(): GetBankCardInfo = GetBankCardInfo(
    bankCardNumber = this.bankCardNumber
)

fun TdApi.GetBotInfoDescription.toDomain(): GetBotInfoDescription = GetBotInfoDescription(
    botUserId = this.botUserId,
    languageCode = this.languageCode
)

fun TdApi.GetBotInfoShortDescription.toDomain(): GetBotInfoShortDescription = GetBotInfoShortDescription(
    botUserId = this.botUserId,
    languageCode = this.languageCode
)

fun TdApi.GetBotMediaPreviewInfo.toDomain(): GetBotMediaPreviewInfo = GetBotMediaPreviewInfo(
    botUserId = this.botUserId,
    languageCode = this.languageCode
)

fun TdApi.GetBotMediaPreviews.toDomain(): GetBotMediaPreviews = GetBotMediaPreviews(
    botUserId = this.botUserId
)

fun TdApi.GetBotSimilarBotCount.toDomain(): GetBotSimilarBotCount = GetBotSimilarBotCount(
    botUserId = this.botUserId,
    returnLocal = this.returnLocal
)

fun TdApi.GetBotSimilarBots.toDomain(): GetBotSimilarBots = GetBotSimilarBots(
    botUserId = this.botUserId
)

fun TdApi.GetBusinessAccountStarAmount.toDomain(): GetBusinessAccountStarAmount = GetBusinessAccountStarAmount(
    businessConnectionId = this.businessConnectionId
)

fun TdApi.GetBusinessChatLinkInfo.toDomain(): GetBusinessChatLinkInfo = GetBusinessChatLinkInfo(
    linkName = this.linkName
)

fun TdApi.GetBusinessChatLinks.toDomain(): GetBusinessChatLinks = GetBusinessChatLinks

fun TdApi.GetBusinessConnectedBot.toDomain(): GetBusinessConnectedBot = GetBusinessConnectedBot

fun TdApi.GetBusinessFeatures.toDomain(): GetBusinessFeatures = GetBusinessFeatures(
    source = this.source.toDomain()
)

fun TdApi.GetCallbackQueryAnswer.toDomain(): GetCallbackQueryAnswer = GetCallbackQueryAnswer(
    chatId = this.chatId,
    messageId = this.messageId,
    payload = this.payload.toDomain()
)

fun TdApi.GetCallbackQueryMessage.toDomain(): GetCallbackQueryMessage = GetCallbackQueryMessage(
    chatId = this.chatId,
    messageId = this.messageId,
    callbackQueryId = this.callbackQueryId
)

fun TdApi.GetCloseFriends.toDomain(): GetCloseFriends = GetCloseFriends

fun TdApi.GetCollectibleItemInfo.toDomain(): GetCollectibleItemInfo = GetCollectibleItemInfo(
    type = this.type.toDomain()
)

fun TdApi.GetCommands.toDomain(): GetCommands = GetCommands(
    scope = this.scope.toDomain(),
    languageCode = this.languageCode
)

fun TdApi.GetConnectedAffiliateProgram.toDomain(): GetConnectedAffiliateProgram = GetConnectedAffiliateProgram(
    affiliate = this.affiliate.toDomain(),
    botUserId = this.botUserId
)

fun TdApi.GetConnectedAffiliatePrograms.toDomain(): GetConnectedAffiliatePrograms = GetConnectedAffiliatePrograms(
    affiliate = this.affiliate.toDomain(),
    offset = this.offset,
    limit = this.limit
)

fun TdApi.GetCountries.toDomain(): GetCountries = GetCountries

fun TdApi.GetCountryCode.toDomain(): GetCountryCode = GetCountryCode

fun TdApi.GetCountryFlagEmoji.toDomain(): GetCountryFlagEmoji = GetCountryFlagEmoji(
    countryCode = this.countryCode
)

fun TdApi.GetCreatedPublicChats.toDomain(): GetCreatedPublicChats = GetCreatedPublicChats(
    type = this.type.toDomain()
)

fun TdApi.GetCurrentState.toDomain(): GetCurrentState = GetCurrentState

fun TdApi.GetCurrentWeather.toDomain(): GetCurrentWeather = GetCurrentWeather(
    location = this.location.toDomain()
)

fun TdApi.GetCustomEmojiReactionAnimations.toDomain(): GetCustomEmojiReactionAnimations = GetCustomEmojiReactionAnimations

fun TdApi.GetCustomEmojiStickers.toDomain(): GetCustomEmojiStickers = GetCustomEmojiStickers(
    customEmojiIds = this.customEmojiIds
)

fun TdApi.GetDatabaseStatistics.toDomain(): GetDatabaseStatistics = GetDatabaseStatistics

fun TdApi.GetDeepLinkInfo.toDomain(): GetDeepLinkInfo = GetDeepLinkInfo(
    link = this.link
)

fun TdApi.GetDefaultBackgroundCustomEmojiStickers.toDomain(): GetDefaultBackgroundCustomEmojiStickers = GetDefaultBackgroundCustomEmojiStickers

fun TdApi.GetDefaultChatEmojiStatuses.toDomain(): GetDefaultChatEmojiStatuses = GetDefaultChatEmojiStatuses

fun TdApi.GetDefaultChatPhotoCustomEmojiStickers.toDomain(): GetDefaultChatPhotoCustomEmojiStickers = GetDefaultChatPhotoCustomEmojiStickers

fun TdApi.GetDefaultEmojiStatuses.toDomain(): GetDefaultEmojiStatuses = GetDefaultEmojiStatuses

fun TdApi.GetDefaultMessageAutoDeleteTime.toDomain(): GetDefaultMessageAutoDeleteTime = GetDefaultMessageAutoDeleteTime

fun TdApi.GetDefaultProfilePhotoCustomEmojiStickers.toDomain(): GetDefaultProfilePhotoCustomEmojiStickers = GetDefaultProfilePhotoCustomEmojiStickers

fun TdApi.GetDirectMessagesChatTopic.toDomain(): GetDirectMessagesChatTopic = GetDirectMessagesChatTopic(
    chatId = this.chatId,
    topicId = this.topicId
)

fun TdApi.GetDirectMessagesChatTopicHistory.toDomain(): GetDirectMessagesChatTopicHistory = GetDirectMessagesChatTopicHistory(
    chatId = this.chatId,
    topicId = this.topicId,
    fromMessageId = this.fromMessageId,
    offset = this.offset,
    limit = this.limit
)

fun TdApi.GetDirectMessagesChatTopicMessageByDate.toDomain(): GetDirectMessagesChatTopicMessageByDate = GetDirectMessagesChatTopicMessageByDate(
    chatId = this.chatId,
    topicId = this.topicId,
    date = this.date
)

fun TdApi.GetDirectMessagesChatTopicRevenue.toDomain(): GetDirectMessagesChatTopicRevenue = GetDirectMessagesChatTopicRevenue(
    chatId = this.chatId,
    topicId = this.topicId
)

fun TdApi.GetDisallowedChatEmojiStatuses.toDomain(): GetDisallowedChatEmojiStatuses = GetDisallowedChatEmojiStatuses

fun TdApi.GetEmojiCategories.toDomain(): GetEmojiCategories = GetEmojiCategories(
    type = this.type.toDomain()
)

fun TdApi.GetEmojiReaction.toDomain(): GetEmojiReaction = GetEmojiReaction(
    emoji = this.emoji
)

fun TdApi.GetEmojiSuggestionsUrl.toDomain(): GetEmojiSuggestionsUrl = GetEmojiSuggestionsUrl(
    languageCode = this.languageCode
)

fun TdApi.GetExternalLink.toDomain(): GetExternalLink = GetExternalLink(
    link = this.link,
    allowWriteAccess = this.allowWriteAccess
)

fun TdApi.GetExternalLinkInfo.toDomain(): GetExternalLinkInfo = GetExternalLinkInfo(
    link = this.link
)

fun TdApi.GetFavoriteStickers.toDomain(): GetFavoriteStickers = GetFavoriteStickers

fun TdApi.GetGameHighScores.toDomain(): GetGameHighScores = GetGameHighScores(
    chatId = this.chatId,
    messageId = this.messageId,
    userId = this.userId
)

fun TdApi.GetGiftChatThemes.toDomain(): GetGiftChatThemes = GetGiftChatThemes(
    offset = this.offset,
    limit = this.limit
)

fun TdApi.GetGiftCollections.toDomain(): GetGiftCollections = GetGiftCollections(
    ownerId = this.ownerId.toDomain()
)

fun TdApi.GetGiftUpgradePreview.toDomain(): GetGiftUpgradePreview = GetGiftUpgradePreview(
    giftId = this.giftId
)

fun TdApi.GetGiveawayInfo.toDomain(): GetGiveawayInfo = GetGiveawayInfo(
    chatId = this.chatId,
    messageId = this.messageId
)

fun TdApi.GetGreetingStickers.toDomain(): GetGreetingStickers = GetGreetingStickers

fun TdApi.GetGrossingWebAppBots.toDomain(): GetGrossingWebAppBots = GetGrossingWebAppBots(
    offset = this.offset,
    limit = this.limit
)

fun TdApi.GetGroupsInCommon.toDomain(): GetGroupsInCommon = GetGroupsInCommon(
    userId = this.userId,
    offsetChatId = this.offsetChatId,
    limit = this.limit
)

fun TdApi.GetImportedContactCount.toDomain(): GetImportedContactCount = GetImportedContactCount

fun TdApi.GetInactiveSupergroupChats.toDomain(): GetInactiveSupergroupChats = GetInactiveSupergroupChats

fun TdApi.GetInlineGameHighScores.toDomain(): GetInlineGameHighScores = GetInlineGameHighScores(
    inlineMessageId = this.inlineMessageId,
    userId = this.userId
)

fun TdApi.GetInlineQueryResults.toDomain(): GetInlineQueryResults = GetInlineQueryResults(
    botUserId = this.botUserId,
    chatId = this.chatId,
    userLocation = this.userLocation.toDomain(),
    query = this.query,
    offset = this.offset
)

fun TdApi.GetInstalledBackgrounds.toDomain(): GetInstalledBackgrounds = GetInstalledBackgrounds(
    forDarkTheme = this.forDarkTheme
)

fun TdApi.GetInstalledStickerSets.toDomain(): GetInstalledStickerSets = GetInstalledStickerSets(
    stickerType = this.stickerType.toDomain()
)

fun TdApi.GetInternalLink.toDomain(): GetInternalLink = GetInternalLink(
    type = this.type.toDomain(),
    isHttp = this.isHttp
)

fun TdApi.GetInternalLinkType.toDomain(): GetInternalLinkType = GetInternalLinkType(
    link = this.link
)

fun TdApi.GetJsonString.toDomain(): GetJsonString = GetJsonString(
    jsonValue = this.jsonValue.toDomain()
)

fun TdApi.GetJsonValue.toDomain(): GetJsonValue = GetJsonValue(
    json = this.json
)

fun TdApi.GetKeywordEmojis.toDomain(): GetKeywordEmojis = GetKeywordEmojis(
    text = this.text,
    inputLanguageCodes = this.inputLanguageCodes.toList()
)

fun TdApi.GetLanguagePackInfo.toDomain(): GetLanguagePackInfo = GetLanguagePackInfo(
    languagePackId = this.languagePackId
)

fun TdApi.GetLanguagePackString.toDomain(): GetLanguagePackString = GetLanguagePackString(
    languagePackDatabasePath = this.languagePackDatabasePath,
    localizationTarget = this.localizationTarget,
    languagePackId = this.languagePackId,
    key = this.key
)

fun TdApi.GetLanguagePackStrings.toDomain(): GetLanguagePackStrings = GetLanguagePackStrings(
    languagePackId = this.languagePackId,
    keys = this.keys.toList()
)

fun TdApi.GetLinkPreview.toDomain(): GetLinkPreview = GetLinkPreview(
    text = this.text.toDomain(),
    linkPreviewOptions = this.linkPreviewOptions.toDomain()
)

fun TdApi.GetLocalizationTargetInfo.toDomain(): GetLocalizationTargetInfo = GetLocalizationTargetInfo(
    onlyLocal = this.onlyLocal
)

fun TdApi.GetLogTagVerbosityLevel.toDomain(): GetLogTagVerbosityLevel = GetLogTagVerbosityLevel(
    tag = this.tag
)

fun TdApi.GetLoginUrl.toDomain(): GetLoginUrl = GetLoginUrl(
    chatId = this.chatId,
    messageId = this.messageId,
    buttonId = this.buttonId,
    allowWriteAccess = this.allowWriteAccess
)

fun TdApi.GetLoginUrlInfo.toDomain(): GetLoginUrlInfo = GetLoginUrlInfo(
    chatId = this.chatId,
    messageId = this.messageId,
    buttonId = this.buttonId
)

fun TdApi.GetMainWebApp.toDomain(): GetMainWebApp = GetMainWebApp(
    chatId = this.chatId,
    botUserId = this.botUserId,
    startParameter = this.startParameter,
    parameters = this.parameters.toDomain()
)

fun TdApi.GetMapThumbnailFile.toDomain(): GetMapThumbnailFile = GetMapThumbnailFile(
    location = this.location.toDomain(),
    zoom = this.zoom,
    width = this.width,
    height = this.height,
    scale = this.scale,
    chatId = this.chatId
)

fun TdApi.GetMarkdownText.toDomain(): GetMarkdownText = GetMarkdownText(
    text = this.text.toDomain()
)

fun TdApi.GetNewChatPrivacySettings.toDomain(): GetNewChatPrivacySettings = GetNewChatPrivacySettings

fun TdApi.GetOwnedBots.toDomain(): GetOwnedBots = GetOwnedBots

fun TdApi.GetOwnedStickerSets.toDomain(): GetOwnedStickerSets = GetOwnedStickerSets(
    offsetStickerSetId = this.offsetStickerSetId,
    limit = this.limit
)

fun TdApi.GetPaidMessageRevenue.toDomain(): GetPaidMessageRevenue = GetPaidMessageRevenue(
    userId = this.userId
)

fun TdApi.GetPasswordState.toDomain(): GetPasswordState = GetPasswordState

fun TdApi.GetPhoneNumberInfo.toDomain(): GetPhoneNumberInfo = GetPhoneNumberInfo(
    phoneNumberPrefix = this.phoneNumberPrefix
)

fun TdApi.GetPhoneNumberInfoSync.toDomain(): GetPhoneNumberInfoSync = GetPhoneNumberInfoSync(
    languageCode = this.languageCode,
    phoneNumberPrefix = this.phoneNumberPrefix
)

fun TdApi.GetPollVoters.toDomain(): GetPollVoters = GetPollVoters(
    chatId = this.chatId,
    messageId = this.messageId,
    optionId = this.optionId,
    offset = this.offset,
    limit = this.limit
)

fun TdApi.GetPreferredCountryLanguage.toDomain(): GetPreferredCountryLanguage = GetPreferredCountryLanguage(
    countryCode = this.countryCode
)

fun TdApi.GetPremiumGiftPaymentOptions.toDomain(): GetPremiumGiftPaymentOptions = GetPremiumGiftPaymentOptions

fun TdApi.GetPremiumGiveawayPaymentOptions.toDomain(): GetPremiumGiveawayPaymentOptions = GetPremiumGiveawayPaymentOptions(
    boostedChatId = this.boostedChatId
)

fun TdApi.GetPremiumInfoSticker.toDomain(): GetPremiumInfoSticker = GetPremiumInfoSticker(
    monthCount = this.monthCount
)

fun TdApi.GetPremiumStickerExamples.toDomain(): GetPremiumStickerExamples = GetPremiumStickerExamples

fun TdApi.GetPremiumStickers.toDomain(): GetPremiumStickers = GetPremiumStickers(
    limit = this.limit
)

fun TdApi.GetPreparedInlineMessage.toDomain(): GetPreparedInlineMessage = GetPreparedInlineMessage(
    botUserId = this.botUserId,
    preparedMessageId = this.preparedMessageId
)

fun TdApi.GetPublicPostSearchLimits.toDomain(): GetPublicPostSearchLimits = GetPublicPostSearchLimits(
    query = this.query
)

fun TdApi.GetPushReceiverId.toDomain(): GetPushReceiverId = GetPushReceiverId(
    payload = this.payload
)

fun TdApi.GetReadDatePrivacySettings.toDomain(): GetReadDatePrivacySettings = GetReadDatePrivacySettings

fun TdApi.GetReceivedGift.toDomain(): GetReceivedGift = GetReceivedGift(
    receivedGiftId = this.receivedGiftId
)

fun TdApi.GetReceivedGifts.toDomain(): GetReceivedGifts = GetReceivedGifts(
    businessConnectionId = this.businessConnectionId,
    ownerId = this.ownerId.toDomain(),
    collectionId = this.collectionId,
    excludeUnsaved = this.excludeUnsaved,
    excludeSaved = this.excludeSaved,
    excludeUnlimited = this.excludeUnlimited,
    excludeUpgradable = this.excludeUpgradable,
    excludeNonUpgradable = this.excludeNonUpgradable,
    excludeUpgraded = this.excludeUpgraded,
    sortByPrice = this.sortByPrice,
    offset = this.offset,
    limit = this.limit
)

fun TdApi.GetRecentEmojiStatuses.toDomain(): GetRecentEmojiStatuses = GetRecentEmojiStatuses

fun TdApi.GetRecentInlineBots.toDomain(): GetRecentInlineBots = GetRecentInlineBots

fun TdApi.GetRecentStickers.toDomain(): GetRecentStickers = GetRecentStickers(
    isAttached = this.isAttached
)

fun TdApi.GetRecentlyOpenedChats.toDomain(): GetRecentlyOpenedChats = GetRecentlyOpenedChats(
    limit = this.limit
)

fun TdApi.GetRecentlyVisitedTMeUrls.toDomain(): GetRecentlyVisitedTMeUrls = GetRecentlyVisitedTMeUrls(
    referrer = this.referrer
)

fun TdApi.GetRecommendedChatFolders.toDomain(): GetRecommendedChatFolders = GetRecommendedChatFolders

fun TdApi.GetRecommendedChats.toDomain(): GetRecommendedChats = GetRecommendedChats

fun TdApi.GetRecoveryEmailAddress.toDomain(): GetRecoveryEmailAddress = GetRecoveryEmailAddress(
    password = this.password
)

fun TdApi.GetRepliedMessage.toDomain(): GetRepliedMessage = GetRepliedMessage(
    chatId = this.chatId,
    messageId = this.messageId
)

fun TdApi.GetSavedAnimations.toDomain(): GetSavedAnimations = GetSavedAnimations

fun TdApi.GetSavedMessagesTags.toDomain(): GetSavedMessagesTags = GetSavedMessagesTags(
    savedMessagesTopicId = this.savedMessagesTopicId
)

fun TdApi.GetSavedMessagesTopicHistory.toDomain(): GetSavedMessagesTopicHistory = GetSavedMessagesTopicHistory(
    savedMessagesTopicId = this.savedMessagesTopicId,
    fromMessageId = this.fromMessageId,
    offset = this.offset,
    limit = this.limit
)

fun TdApi.GetSavedMessagesTopicMessageByDate.toDomain(): GetSavedMessagesTopicMessageByDate = GetSavedMessagesTopicMessageByDate(
    savedMessagesTopicId = this.savedMessagesTopicId,
    date = this.date
)

fun TdApi.GetSavedNotificationSound.toDomain(): GetSavedNotificationSound = GetSavedNotificationSound(
    notificationSoundId = this.notificationSoundId
)

fun TdApi.GetSavedNotificationSounds.toDomain(): GetSavedNotificationSounds = GetSavedNotificationSounds

fun TdApi.GetScopeNotificationSettings.toDomain(): GetScopeNotificationSettings = GetScopeNotificationSettings(
    scope = this.scope.toDomain()
)

fun TdApi.GetSearchSponsoredChats.toDomain(): GetSearchSponsoredChats = GetSearchSponsoredChats(
    query = this.query
)

fun TdApi.GetSearchedForTags.toDomain(): GetSearchedForTags = GetSearchedForTags(
    tagPrefix = this.tagPrefix,
    limit = this.limit
)

fun TdApi.GetSecretChat.toDomain(): GetSecretChat = GetSecretChat(
    secretChatId = this.secretChatId
)

fun TdApi.GetStarAdAccountUrl.toDomain(): GetStarAdAccountUrl = GetStarAdAccountUrl(
    ownerId = this.ownerId.toDomain()
)

fun TdApi.GetStarGiftPaymentOptions.toDomain(): GetStarGiftPaymentOptions = GetStarGiftPaymentOptions(
    userId = this.userId
)

fun TdApi.GetStarGiveawayPaymentOptions.toDomain(): GetStarGiveawayPaymentOptions = GetStarGiveawayPaymentOptions

fun TdApi.GetStarPaymentOptions.toDomain(): GetStarPaymentOptions = GetStarPaymentOptions

fun TdApi.GetStarSubscriptions.toDomain(): GetStarSubscriptions = GetStarSubscriptions(
    onlyExpiring = this.onlyExpiring,
    offset = this.offset
)

fun TdApi.GetStarTransactions.toDomain(): GetStarTransactions = GetStarTransactions(
    ownerId = this.ownerId.toDomain(),
    subscriptionId = this.subscriptionId,
    direction = this.direction.toDomain(),
    offset = this.offset,
    limit = this.limit
)

fun TdApi.GetStatisticalGraph.toDomain(): GetStatisticalGraph = GetStatisticalGraph(
    chatId = this.chatId,
    token = this.token,
    x = this.x
)

fun TdApi.GetStickerEmojis.toDomain(): GetStickerEmojis = GetStickerEmojis(
    sticker = this.sticker.toDomain()
)

fun TdApi.GetStickerOutline.toDomain(): GetStickerOutline = GetStickerOutline(
    stickerFileId = this.stickerFileId,
    forAnimatedEmoji = this.forAnimatedEmoji,
    forClickedAnimatedEmojiMessage = this.forClickedAnimatedEmojiMessage
)

fun TdApi.GetStickerSet.toDomain(): GetStickerSet = GetStickerSet(
    setId = this.setId
)

fun TdApi.GetStickerSetName.toDomain(): GetStickerSetName = GetStickerSetName(
    setId = this.setId
)

fun TdApi.GetStickers.toDomain(): GetStickers = GetStickers(
    stickerType = this.stickerType.toDomain(),
    query = this.query,
    limit = this.limit,
    chatId = this.chatId
)

fun TdApi.GetStorageStatistics.toDomain(): GetStorageStatistics = GetStorageStatistics(
    chatLimit = this.chatLimit
)

fun TdApi.GetStorageStatisticsFast.toDomain(): GetStorageStatisticsFast = GetStorageStatisticsFast

fun TdApi.GetSuggestedFileName.toDomain(): GetSuggestedFileName = GetSuggestedFileName(
    fileId = this.fileId,
    directory = this.directory
)

fun TdApi.GetSuggestedStickerSetName.toDomain(): GetSuggestedStickerSetName = GetSuggestedStickerSetName(
    title = this.title
)

fun TdApi.GetSuitableDiscussionChats.toDomain(): GetSuitableDiscussionChats = GetSuitableDiscussionChats

fun TdApi.GetSuitablePersonalChats.toDomain(): GetSuitablePersonalChats = GetSuitablePersonalChats

fun TdApi.GetSupportName.toDomain(): GetSupportName = GetSupportName

fun TdApi.GetSupportUser.toDomain(): GetSupportUser = GetSupportUser

fun TdApi.GetTemporaryPasswordState.toDomain(): GetTemporaryPasswordState = GetTemporaryPasswordState

fun TdApi.GetTextEntities.toDomain(): GetTextEntities = GetTextEntities(
    text = this.text
)

fun TdApi.GetThemeParametersJsonString.toDomain(): GetThemeParametersJsonString = GetThemeParametersJsonString(
    theme = this.theme.toDomain()
)

fun TdApi.GetThemedChatEmojiStatuses.toDomain(): GetThemedChatEmojiStatuses = GetThemedChatEmojiStatuses

fun TdApi.GetThemedEmojiStatuses.toDomain(): GetThemedEmojiStatuses = GetThemedEmojiStatuses

fun TdApi.GetTimeZones.toDomain(): GetTimeZones = GetTimeZones

fun TdApi.GetTonRevenueStatistics.toDomain(): GetTonRevenueStatistics = GetTonRevenueStatistics(
    isDark = this.isDark
)

fun TdApi.GetTonTransactions.toDomain(): GetTonTransactions = GetTonTransactions(
    direction = this.direction.toDomain(),
    offset = this.offset,
    limit = this.limit
)

fun TdApi.GetTonWithdrawalUrl.toDomain(): GetTonWithdrawalUrl = GetTonWithdrawalUrl(
    password = this.password
)

fun TdApi.GetTopChats.toDomain(): GetTopChats = GetTopChats(
    category = this.category.toDomain(),
    limit = this.limit
)

fun TdApi.GetTrendingStickerSets.toDomain(): GetTrendingStickerSets = GetTrendingStickerSets(
    stickerType = this.stickerType.toDomain(),
    offset = this.offset,
    limit = this.limit
)

fun TdApi.GetUpgradedGift.toDomain(): GetUpgradedGift = GetUpgradedGift(
    name = this.name
)

fun TdApi.GetUpgradedGiftEmojiStatuses.toDomain(): GetUpgradedGiftEmojiStatuses = GetUpgradedGiftEmojiStatuses

fun TdApi.GetUpgradedGiftValueInfo.toDomain(): GetUpgradedGiftValueInfo = GetUpgradedGiftValueInfo(
    name = this.name
)

fun TdApi.GetUpgradedGiftWithdrawalUrl.toDomain(): GetUpgradedGiftWithdrawalUrl = GetUpgradedGiftWithdrawalUrl(
    receivedGiftId = this.receivedGiftId,
    password = this.password
)

fun TdApi.GetVideoChatAvailableParticipants.toDomain(): GetVideoChatAvailableParticipants = GetVideoChatAvailableParticipants(
    chatId = this.chatId
)

fun TdApi.GetVideoChatInviteLink.toDomain(): GetVideoChatInviteLink = GetVideoChatInviteLink(
    groupCallId = this.groupCallId,
    canSelfUnmute = this.canSelfUnmute
)

fun TdApi.GetVideoChatRtmpUrl.toDomain(): GetVideoChatRtmpUrl = GetVideoChatRtmpUrl(
    chatId = this.chatId
)

fun TdApi.GetVideoChatStreamSegment.toDomain(): GetVideoChatStreamSegment = GetVideoChatStreamSegment(
    groupCallId = this.groupCallId,
    timeOffset = this.timeOffset,
    scale = this.scale,
    channelId = this.channelId,
    videoQuality = this.videoQuality.toDomain()
)

fun TdApi.GetVideoChatStreams.toDomain(): GetVideoChatStreams = GetVideoChatStreams(
    groupCallId = this.groupCallId
)

fun TdApi.GetVideoMessageAdvertisements.toDomain(): GetVideoMessageAdvertisements = GetVideoMessageAdvertisements(
    chatId = this.chatId,
    messageId = this.messageId
)

fun TdApi.GetWebAppLinkUrl.toDomain(): GetWebAppLinkUrl = GetWebAppLinkUrl(
    chatId = this.chatId,
    botUserId = this.botUserId,
    webAppShortName = this.webAppShortName,
    startParameter = this.startParameter,
    allowWriteAccess = this.allowWriteAccess,
    parameters = this.parameters.toDomain()
)

fun TdApi.GetWebAppPlaceholder.toDomain(): GetWebAppPlaceholder = GetWebAppPlaceholder(
    botUserId = this.botUserId
)

fun TdApi.GetWebPageInstantView.toDomain(): GetWebPageInstantView = GetWebPageInstantView(
    url = this.url,
    onlyLocal = this.onlyLocal
)

