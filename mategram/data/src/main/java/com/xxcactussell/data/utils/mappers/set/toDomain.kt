package com.xxcactussell.data.utils.mappers.set

import com.xxcactussell.data.utils.mappers.affiliate.toDomain
import com.xxcactussell.data.utils.mappers.auto.toDomain
import com.xxcactussell.data.utils.mappers.autosave.toDomain
import com.xxcactussell.data.utils.mappers.background.toDomain
import com.xxcactussell.data.utils.mappers.birthdate.toDomain
import com.xxcactussell.data.utils.mappers.block.toDomain
import com.xxcactussell.data.utils.mappers.bots.toDomain
import com.xxcactussell.data.utils.mappers.business.toDomain
import com.xxcactussell.data.utils.mappers.chat.toDomain
import com.xxcactussell.data.utils.mappers.emoji.toDomain
import com.xxcactussell.data.utils.mappers.formatted.toDomain
import com.xxcactussell.data.utils.mappers.input.toDomain
import com.xxcactussell.data.utils.mappers.language.toDomain
import com.xxcactussell.data.utils.mappers.mask.toDomain
import com.xxcactussell.data.utils.mappers.message.toDomain
import com.xxcactussell.data.utils.mappers.monetization.toDomain
import com.xxcactussell.data.utils.mappers.network.toDomain
import com.xxcactussell.data.utils.mappers.new.toDomain
import com.xxcactussell.data.utils.mappers.notification.toDomain
import com.xxcactussell.data.utils.mappers.paid.toDomain
import com.xxcactussell.data.utils.mappers.profile.toDomain
import com.xxcactussell.data.utils.mappers.reaction.toDomain
import com.xxcactussell.data.utils.mappers.read.toDomain
import com.xxcactussell.data.utils.mappers.scope.toDomain
import com.xxcactussell.data.utils.mappers.sticker.toDomain
import com.xxcactussell.data.utils.mappers.store.toDomain
import com.xxcactussell.data.utils.mappers.stories.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.SetAccentColor.toDomain(): SetAccentColor = SetAccentColor(
    accentColorId = this.accentColorId,
    backgroundCustomEmojiId = this.backgroundCustomEmojiId
)

fun TdApi.SetAlarm.toDomain(): SetAlarm = SetAlarm(
    seconds = this.seconds
)

fun TdApi.SetApplicationVerificationToken.toDomain(): SetApplicationVerificationToken = SetApplicationVerificationToken(
    verificationId = this.verificationId,
    token = this.token
)

fun TdApi.SetArchiveChatListSettings.toDomain(): SetArchiveChatListSettings = SetArchiveChatListSettings(
    settings = this.settings.toDomain()
)

fun TdApi.SetAuthenticationEmailAddress.toDomain(): SetAuthenticationEmailAddress = SetAuthenticationEmailAddress(
    emailAddress = this.emailAddress
)

fun TdApi.SetAuthenticationPremiumPurchaseTransaction.toDomain(): SetAuthenticationPremiumPurchaseTransaction = SetAuthenticationPremiumPurchaseTransaction(
    transaction = this.transaction.toDomain(),
    isRestore = this.isRestore,
    currency = this.currency,
    amount = this.amount
)

fun TdApi.SetAutoDownloadSettings.toDomain(): SetAutoDownloadSettings = SetAutoDownloadSettings(
    settings = this.settings.toDomain(),
    type = this.type.toDomain()
)

fun TdApi.SetAutosaveSettings.toDomain(): SetAutosaveSettings = SetAutosaveSettings(
    scope = this.scope.toDomain(),
    settings = this.settings.toDomain()
)

fun TdApi.SetBio.toDomain(): SetBio = SetBio(
    bio = this.bio
)

fun TdApi.SetBirthdate.toDomain(): SetBirthdate = SetBirthdate(
    birthdate = this.birthdate.toDomain()
)

fun TdApi.SetBotInfoDescription.toDomain(): SetBotInfoDescription = SetBotInfoDescription(
    botUserId = this.botUserId,
    languageCode = this.languageCode,
    description = this.description
)

fun TdApi.SetBotInfoShortDescription.toDomain(): SetBotInfoShortDescription = SetBotInfoShortDescription(
    botUserId = this.botUserId,
    languageCode = this.languageCode,
    shortDescription = this.shortDescription
)

fun TdApi.SetBotProfilePhoto.toDomain(): SetBotProfilePhoto = SetBotProfilePhoto(
    botUserId = this.botUserId,
    photo = this.photo.toDomain()
)

fun TdApi.SetBotUpdatesStatus.toDomain(): SetBotUpdatesStatus = SetBotUpdatesStatus(
    pendingUpdateCount = this.pendingUpdateCount,
    errorMessage = this.errorMessage
)

fun TdApi.SetBusinessAccountBio.toDomain(): SetBusinessAccountBio = SetBusinessAccountBio(
    businessConnectionId = this.businessConnectionId,
    bio = this.bio
)

fun TdApi.SetBusinessAccountGiftSettings.toDomain(): SetBusinessAccountGiftSettings = SetBusinessAccountGiftSettings(
    businessConnectionId = this.businessConnectionId,
    settings = this.settings.toDomain()
)

fun TdApi.SetBusinessAccountName.toDomain(): SetBusinessAccountName = SetBusinessAccountName(
    businessConnectionId = this.businessConnectionId,
    firstName = this.firstName,
    lastName = this.lastName
)

fun TdApi.SetBusinessAccountProfilePhoto.toDomain(): SetBusinessAccountProfilePhoto = SetBusinessAccountProfilePhoto(
    businessConnectionId = this.businessConnectionId,
    photo = this.photo.toDomain(),
    isPublic = this.isPublic
)

fun TdApi.SetBusinessAccountUsername.toDomain(): SetBusinessAccountUsername = SetBusinessAccountUsername(
    businessConnectionId = this.businessConnectionId,
    username = this.username
)

fun TdApi.SetBusinessAwayMessageSettings.toDomain(): SetBusinessAwayMessageSettings = SetBusinessAwayMessageSettings(
    awayMessageSettings = this.awayMessageSettings.toDomain()
)

fun TdApi.SetBusinessConnectedBot.toDomain(): SetBusinessConnectedBot = SetBusinessConnectedBot(
    bot = this.bot.toDomain()
)

fun TdApi.SetBusinessGreetingMessageSettings.toDomain(): SetBusinessGreetingMessageSettings = SetBusinessGreetingMessageSettings(
    greetingMessageSettings = this.greetingMessageSettings.toDomain()
)

fun TdApi.SetBusinessLocation.toDomain(): SetBusinessLocation = SetBusinessLocation(
    location = this.location.toDomain()
)

fun TdApi.SetBusinessMessageIsPinned.toDomain(): SetBusinessMessageIsPinned = SetBusinessMessageIsPinned(
    businessConnectionId = this.businessConnectionId,
    chatId = this.chatId,
    messageId = this.messageId,
    isPinned = this.isPinned
)

fun TdApi.SetBusinessOpeningHours.toDomain(): SetBusinessOpeningHours = SetBusinessOpeningHours(
    openingHours = this.openingHours.toDomain()
)

fun TdApi.SetBusinessStartPage.toDomain(): SetBusinessStartPage = SetBusinessStartPage(
    startPage = this.startPage.toDomain()
)

fun TdApi.SetChatAccentColor.toDomain(): SetChatAccentColor = SetChatAccentColor(
    chatId = this.chatId,
    accentColorId = this.accentColorId,
    backgroundCustomEmojiId = this.backgroundCustomEmojiId
)

fun TdApi.SetChatActiveStoriesList.toDomain(): SetChatActiveStoriesList = SetChatActiveStoriesList(
    chatId = this.chatId,
    storyList = this.storyList.toDomain()
)

fun TdApi.SetChatAffiliateProgram.toDomain(): SetChatAffiliateProgram = SetChatAffiliateProgram(
    chatId = this.chatId,
    parameters = this.parameters.toDomain()
)

fun TdApi.SetChatBackground.toDomain(): SetChatBackground = SetChatBackground(
    chatId = this.chatId,
    background = this.background.toDomain(),
    type = this.type.toDomain(),
    darkThemeDimming = this.darkThemeDimming,
    onlyForSelf = this.onlyForSelf
)

fun TdApi.SetChatDirectMessagesGroup.toDomain(): SetChatDirectMessagesGroup = SetChatDirectMessagesGroup(
    chatId = this.chatId,
    isEnabled = this.isEnabled,
    paidMessageStarCount = this.paidMessageStarCount
)

fun TdApi.SetChatEmojiStatus.toDomain(): SetChatEmojiStatus = SetChatEmojiStatus(
    chatId = this.chatId,
    emojiStatus = this.emojiStatus.toDomain()
)

fun TdApi.SetChatLocation.toDomain(): SetChatLocation = SetChatLocation(
    chatId = this.chatId,
    location = this.location.toDomain()
)

fun TdApi.SetChatMemberStatus.toDomain(): SetChatMemberStatus = SetChatMemberStatus(
    chatId = this.chatId,
    memberId = this.memberId.toDomain(),
    status = this.status.toDomain()
)

fun TdApi.SetChatMessageAutoDeleteTime.toDomain(): SetChatMessageAutoDeleteTime = SetChatMessageAutoDeleteTime(
    chatId = this.chatId,
    messageAutoDeleteTime = this.messageAutoDeleteTime
)

fun TdApi.SetChatMessageSender.toDomain(): SetChatMessageSender = SetChatMessageSender(
    chatId = this.chatId,
    messageSenderId = this.messageSenderId.toDomain()
)

fun TdApi.SetChatPaidMessageStarCount.toDomain(): SetChatPaidMessageStarCount = SetChatPaidMessageStarCount(
    chatId = this.chatId,
    paidMessageStarCount = this.paidMessageStarCount
)

fun TdApi.SetChatPinnedStories.toDomain(): SetChatPinnedStories = SetChatPinnedStories(
    chatId = this.chatId,
    storyIds = this.storyIds
)

fun TdApi.SetChatProfileAccentColor.toDomain(): SetChatProfileAccentColor = SetChatProfileAccentColor(
    chatId = this.chatId,
    profileAccentColorId = this.profileAccentColorId,
    profileBackgroundCustomEmojiId = this.profileBackgroundCustomEmojiId
)

fun TdApi.SetChatSlowModeDelay.toDomain(): SetChatSlowModeDelay = SetChatSlowModeDelay(
    chatId = this.chatId,
    slowModeDelay = this.slowModeDelay
)

fun TdApi.SetChatTheme.toDomain(): SetChatTheme = SetChatTheme(
    chatId = this.chatId,
    theme = this.theme.toDomain()
)

fun TdApi.SetCloseFriends.toDomain(): SetCloseFriends = SetCloseFriends(
    userIds = this.userIds
)

fun TdApi.SetCommands.toDomain(): SetCommands = SetCommands(
    scope = this.scope.toDomain(),
    languageCode = this.languageCode,
    commands = this.commands.map { it.toDomain() }
)

fun TdApi.SetCustomEmojiStickerSetThumbnail.toDomain(): SetCustomEmojiStickerSetThumbnail = SetCustomEmojiStickerSetThumbnail(
    name = this.name,
    customEmojiId = this.customEmojiId
)

fun TdApi.SetCustomLanguagePack.toDomain(): SetCustomLanguagePack = SetCustomLanguagePack(
    info = this.info.toDomain(),
    strings = this.strings.map { it.toDomain() }
)

fun TdApi.SetCustomLanguagePackString.toDomain(): SetCustomLanguagePackString = SetCustomLanguagePackString(
    languagePackId = this.languagePackId,
    newString = this.newString.toDomain()
)

fun TdApi.SetDatabaseEncryptionKey.toDomain(): SetDatabaseEncryptionKey = SetDatabaseEncryptionKey(
    newEncryptionKey = this.newEncryptionKey
)

fun TdApi.SetDefaultBackground.toDomain(): SetDefaultBackground = SetDefaultBackground(
    background = this.background.toDomain(),
    type = this.type.toDomain(),
    forDarkTheme = this.forDarkTheme
)

fun TdApi.SetDefaultChannelAdministratorRights.toDomain(): SetDefaultChannelAdministratorRights = SetDefaultChannelAdministratorRights(
    defaultChannelAdministratorRights = this.defaultChannelAdministratorRights.toDomain()
)

fun TdApi.SetDefaultGroupAdministratorRights.toDomain(): SetDefaultGroupAdministratorRights = SetDefaultGroupAdministratorRights(
    defaultGroupAdministratorRights = this.defaultGroupAdministratorRights.toDomain()
)

fun TdApi.SetDefaultMessageAutoDeleteTime.toDomain(): SetDefaultMessageAutoDeleteTime = SetDefaultMessageAutoDeleteTime(
    messageAutoDeleteTime = this.messageAutoDeleteTime.toDomain()
)

fun TdApi.SetDefaultReactionType.toDomain(): SetDefaultReactionType = SetDefaultReactionType(
    reactionType = this.reactionType.toDomain()
)

fun TdApi.SetDirectMessagesChatTopicDraftMessage.toDomain(): SetDirectMessagesChatTopicDraftMessage = SetDirectMessagesChatTopicDraftMessage(
    chatId = this.chatId,
    topicId = this.topicId,
    draftMessage = this.draftMessage.toDomain()
)

fun TdApi.SetDirectMessagesChatTopicIsMarkedAsUnread.toDomain(): SetDirectMessagesChatTopicIsMarkedAsUnread = SetDirectMessagesChatTopicIsMarkedAsUnread(
    chatId = this.chatId,
    topicId = this.topicId,
    isMarkedAsUnread = this.isMarkedAsUnread
)

fun TdApi.SetEmojiStatus.toDomain(): SetEmojiStatus = SetEmojiStatus(
    emojiStatus = this.emojiStatus.toDomain()
)

fun TdApi.SetGameScore.toDomain(): SetGameScore = SetGameScore(
    chatId = this.chatId,
    messageId = this.messageId,
    editMessage = this.editMessage,
    userId = this.userId,
    score = this.score,
    force = this.force
)

fun TdApi.SetGiftCollectionName.toDomain(): SetGiftCollectionName = SetGiftCollectionName(
    ownerId = this.ownerId.toDomain(),
    collectionId = this.collectionId,
    name = this.name
)

fun TdApi.SetGiftResalePrice.toDomain(): SetGiftResalePrice = SetGiftResalePrice(
    receivedGiftId = this.receivedGiftId,
    price = this.price.toDomain()
)

fun TdApi.SetGiftSettings.toDomain(): SetGiftSettings = SetGiftSettings(
    settings = this.settings.toDomain()
)

fun TdApi.SetGroupCallParticipantIsSpeaking.toDomain(): SetGroupCallParticipantIsSpeaking = SetGroupCallParticipantIsSpeaking(
    groupCallId = this.groupCallId,
    audioSource = this.audioSource,
    isSpeaking = this.isSpeaking
)

fun TdApi.SetInactiveSessionTtl.toDomain(): SetInactiveSessionTtl = SetInactiveSessionTtl(
    inactiveSessionTtlDays = this.inactiveSessionTtlDays
)

fun TdApi.SetInlineGameScore.toDomain(): SetInlineGameScore = SetInlineGameScore(
    inlineMessageId = this.inlineMessageId,
    editMessage = this.editMessage,
    userId = this.userId,
    score = this.score,
    force = this.force
)

fun TdApi.SetLoginEmailAddress.toDomain(): SetLoginEmailAddress = SetLoginEmailAddress(
    newLoginEmailAddress = this.newLoginEmailAddress
)

fun TdApi.SetMainProfileTab.toDomain(): SetMainProfileTab = SetMainProfileTab(
    mainProfileTab = this.mainProfileTab.toDomain()
)

fun TdApi.SetMenuButton.toDomain(): SetMenuButton = SetMenuButton(
    userId = this.userId,
    menuButton = this.menuButton.toDomain()
)

fun TdApi.SetMessageFactCheck.toDomain(): SetMessageFactCheck = SetMessageFactCheck(
    chatId = this.chatId,
    messageId = this.messageId,
    text = this.text.toDomain()
)

fun TdApi.SetMessageReactions.toDomain(): SetMessageReactions = SetMessageReactions(
    chatId = this.chatId,
    messageId = this.messageId,
    reactionTypes = this.reactionTypes.map { it.toDomain() },
    isBig = this.isBig
)

fun TdApi.SetMessageSenderBlockList.toDomain(): SetMessageSenderBlockList = SetMessageSenderBlockList(
    senderId = this.senderId.toDomain(),
    blockList = this.blockList.toDomain()
)

fun TdApi.SetMessageSenderBotVerification.toDomain(): SetMessageSenderBotVerification = SetMessageSenderBotVerification(
    botUserId = this.botUserId,
    verifiedId = this.verifiedId.toDomain(),
    customDescription = this.customDescription
)

fun TdApi.SetName.toDomain(): SetName = SetName(
    firstName = this.firstName,
    lastName = this.lastName
)

fun TdApi.SetNetworkType.toDomain(): SetNetworkType = SetNetworkType(
    type = this.type.toDomain()
)

fun TdApi.SetNewChatPrivacySettings.toDomain(): SetNewChatPrivacySettings = SetNewChatPrivacySettings(
    settings = this.settings.toDomain()
)

fun TdApi.SetPaidMessageReactionType.toDomain(): SetPaidMessageReactionType = SetPaidMessageReactionType(
    chatId = this.chatId,
    messageId = this.messageId,
    type = this.type.toDomain()
)

fun TdApi.SetPassword.toDomain(): SetPassword = SetPassword(
    oldPassword = this.oldPassword,
    newPassword = this.newPassword,
    newHint = this.newHint,
    setRecoveryEmailAddress = this.setRecoveryEmailAddress,
    newRecoveryEmailAddress = this.newRecoveryEmailAddress
)

fun TdApi.SetPersonalChat.toDomain(): SetPersonalChat = SetPersonalChat(
    chatId = this.chatId
)

fun TdApi.SetPinnedChats.toDomain(): SetPinnedChats = SetPinnedChats(
    chatList = this.chatList.toDomain(),
    chatIds = this.chatIds
)

fun TdApi.SetPinnedForumTopics.toDomain(): SetPinnedForumTopics = SetPinnedForumTopics(
    chatId = this.chatId,
    messageThreadIds = this.messageThreadIds
)

fun TdApi.SetPinnedGifts.toDomain(): SetPinnedGifts = SetPinnedGifts(
    ownerId = this.ownerId.toDomain(),
    receivedGiftIds = this.receivedGiftIds.toList()
)

fun TdApi.SetPinnedSavedMessagesTopics.toDomain(): SetPinnedSavedMessagesTopics = SetPinnedSavedMessagesTopics(
    savedMessagesTopicIds = this.savedMessagesTopicIds
)

fun TdApi.SetPollAnswer.toDomain(): SetPollAnswer = SetPollAnswer(
    chatId = this.chatId,
    messageId = this.messageId,
    optionIds = this.optionIds
)

fun TdApi.SetProfileAccentColor.toDomain(): SetProfileAccentColor = SetProfileAccentColor(
    profileAccentColorId = this.profileAccentColorId,
    profileBackgroundCustomEmojiId = this.profileBackgroundCustomEmojiId
)

fun TdApi.SetProfileAudioPosition.toDomain(): SetProfileAudioPosition = SetProfileAudioPosition(
    fileId = this.fileId,
    afterFileId = this.afterFileId
)

fun TdApi.SetProfilePhoto.toDomain(): SetProfilePhoto = SetProfilePhoto(
    photo = this.photo.toDomain(),
    isPublic = this.isPublic
)

fun TdApi.SetQuickReplyShortcutName.toDomain(): SetQuickReplyShortcutName = SetQuickReplyShortcutName(
    shortcutId = this.shortcutId,
    name = this.name
)

fun TdApi.SetReactionNotificationSettings.toDomain(): SetReactionNotificationSettings = SetReactionNotificationSettings(
    notificationSettings = this.notificationSettings.toDomain()
)

fun TdApi.SetReadDatePrivacySettings.toDomain(): SetReadDatePrivacySettings = SetReadDatePrivacySettings(
    settings = this.settings.toDomain()
)

fun TdApi.SetRecoveryEmailAddress.toDomain(): SetRecoveryEmailAddress = SetRecoveryEmailAddress(
    password = this.password,
    newRecoveryEmailAddress = this.newRecoveryEmailAddress
)

fun TdApi.SetSavedMessagesTagLabel.toDomain(): SetSavedMessagesTagLabel = SetSavedMessagesTagLabel(
    tag = this.tag.toDomain(),
    label = this.label
)

fun TdApi.SetScopeNotificationSettings.toDomain(): SetScopeNotificationSettings = SetScopeNotificationSettings(
    scope = this.scope.toDomain(),
    notificationSettings = this.notificationSettings.toDomain()
)

fun TdApi.SetStickerEmojis.toDomain(): SetStickerEmojis = SetStickerEmojis(
    sticker = this.sticker.toDomain(),
    emojis = this.emojis
)

fun TdApi.SetStickerKeywords.toDomain(): SetStickerKeywords = SetStickerKeywords(
    sticker = this.sticker.toDomain(),
    keywords = this.keywords.toList()
)

fun TdApi.SetStickerMaskPosition.toDomain(): SetStickerMaskPosition = SetStickerMaskPosition(
    sticker = this.sticker.toDomain(),
    maskPosition = this.maskPosition.toDomain()
)

fun TdApi.SetStickerPositionInSet.toDomain(): SetStickerPositionInSet = SetStickerPositionInSet(
    sticker = this.sticker.toDomain(),
    position = this.position
)

fun TdApi.SetStickerSetThumbnail.toDomain(): SetStickerSetThumbnail = SetStickerSetThumbnail(
    userId = this.userId,
    name = this.name,
    thumbnail = this.thumbnail.toDomain(),
    format = this.format.toDomain()
)

fun TdApi.SetStickerSetTitle.toDomain(): SetStickerSetTitle = SetStickerSetTitle(
    name = this.name,
    title = this.title
)

fun TdApi.SetStoryAlbumName.toDomain(): SetStoryAlbumName = SetStoryAlbumName(
    chatId = this.chatId,
    storyAlbumId = this.storyAlbumId,
    name = this.name
)

fun TdApi.SetStoryPrivacySettings.toDomain(): SetStoryPrivacySettings = SetStoryPrivacySettings(
    storyId = this.storyId,
    privacySettings = this.privacySettings.toDomain()
)

fun TdApi.SetSupergroupCustomEmojiStickerSet.toDomain(): SetSupergroupCustomEmojiStickerSet = SetSupergroupCustomEmojiStickerSet(
    supergroupId = this.supergroupId,
    customEmojiStickerSetId = this.customEmojiStickerSetId
)

fun TdApi.SetSupergroupMainProfileTab.toDomain(): SetSupergroupMainProfileTab = SetSupergroupMainProfileTab(
    supergroupId = this.supergroupId,
    mainProfileTab = this.mainProfileTab.toDomain()
)

fun TdApi.SetSupergroupStickerSet.toDomain(): SetSupergroupStickerSet = SetSupergroupStickerSet(
    supergroupId = this.supergroupId,
    stickerSetId = this.stickerSetId
)

fun TdApi.SetSupergroupUnrestrictBoostCount.toDomain(): SetSupergroupUnrestrictBoostCount = SetSupergroupUnrestrictBoostCount(
    supergroupId = this.supergroupId,
    unrestrictBoostCount = this.unrestrictBoostCount
)

fun TdApi.SetSupergroupUsername.toDomain(): SetSupergroupUsername = SetSupergroupUsername(
    supergroupId = this.supergroupId,
    username = this.username
)

fun TdApi.SetUserEmojiStatus.toDomain(): SetUserEmojiStatus = SetUserEmojiStatus(
    userId = this.userId,
    emojiStatus = this.emojiStatus.toDomain()
)

fun TdApi.SetUserPersonalProfilePhoto.toDomain(): SetUserPersonalProfilePhoto = SetUserPersonalProfilePhoto(
    userId = this.userId,
    photo = this.photo.toDomain()
)

fun TdApi.SetUserSupportInfo.toDomain(): SetUserSupportInfo = SetUserSupportInfo(
    userId = this.userId,
    message = this.message.toDomain()
)

fun TdApi.SetUsername.toDomain(): SetUsername = SetUsername(
    username = this.username
)

fun TdApi.SetVideoChatDefaultParticipant.toDomain(): SetVideoChatDefaultParticipant = SetVideoChatDefaultParticipant(
    chatId = this.chatId,
    defaultParticipantId = this.defaultParticipantId.toDomain()
)

fun TdApi.SetVideoChatTitle.toDomain(): SetVideoChatTitle = SetVideoChatTitle(
    groupCallId = this.groupCallId,
    title = this.title
)

