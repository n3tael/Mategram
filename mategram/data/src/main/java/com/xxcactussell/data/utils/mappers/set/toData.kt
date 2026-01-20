package com.xxcactussell.data.utils.mappers.set

import com.xxcactussell.data.utils.mappers.affiliate.toData
import com.xxcactussell.data.utils.mappers.auto.toData
import com.xxcactussell.data.utils.mappers.autosave.toData
import com.xxcactussell.data.utils.mappers.background.toData
import com.xxcactussell.data.utils.mappers.birthdate.toData
import com.xxcactussell.data.utils.mappers.block.toData
import com.xxcactussell.data.utils.mappers.bots.toData
import com.xxcactussell.data.utils.mappers.business.toData
import com.xxcactussell.data.utils.mappers.chat.toData
import com.xxcactussell.data.utils.mappers.emoji.toData
import com.xxcactussell.data.utils.mappers.formatted.toData
import com.xxcactussell.data.utils.mappers.input.toData
import com.xxcactussell.data.utils.mappers.language.toData
import com.xxcactussell.data.utils.mappers.mask.toData
import com.xxcactussell.data.utils.mappers.message.toData
import com.xxcactussell.data.utils.mappers.monetization.toData
import com.xxcactussell.data.utils.mappers.network.toData
import com.xxcactussell.data.utils.mappers.new.toData
import com.xxcactussell.data.utils.mappers.notification.toData
import com.xxcactussell.data.utils.mappers.paid.toData
import com.xxcactussell.data.utils.mappers.profile.toData
import com.xxcactussell.data.utils.mappers.reaction.toData
import com.xxcactussell.data.utils.mappers.read.toData
import com.xxcactussell.data.utils.mappers.scope.toData
import com.xxcactussell.data.utils.mappers.sticker.toData
import com.xxcactussell.data.utils.mappers.store.toData
import com.xxcactussell.data.utils.mappers.stories.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun SetAccentColor.toData(): TdApi.SetAccentColor = TdApi.SetAccentColor(
    this.accentColorId,
    this.backgroundCustomEmojiId
)

fun SetAlarm.toData(): TdApi.SetAlarm = TdApi.SetAlarm(
    this.seconds
)

fun SetApplicationVerificationToken.toData(): TdApi.SetApplicationVerificationToken = TdApi.SetApplicationVerificationToken(
    this.verificationId,
    this.token
)

fun SetArchiveChatListSettings.toData(): TdApi.SetArchiveChatListSettings = TdApi.SetArchiveChatListSettings(
    this.settings.toData()
)

fun SetAuthenticationEmailAddress.toData(): TdApi.SetAuthenticationEmailAddress = TdApi.SetAuthenticationEmailAddress(
    this.emailAddress
)

fun SetAuthenticationPremiumPurchaseTransaction.toData(): TdApi.SetAuthenticationPremiumPurchaseTransaction = TdApi.SetAuthenticationPremiumPurchaseTransaction(
    this.transaction.toData(),
    this.isRestore,
    this.currency,
    this.amount
)

fun SetAutoDownloadSettings.toData(): TdApi.SetAutoDownloadSettings = TdApi.SetAutoDownloadSettings(
    this.settings.toData(),
    this.type.toData()
)

fun SetAutosaveSettings.toData(): TdApi.SetAutosaveSettings = TdApi.SetAutosaveSettings(
    this.scope.toData(),
    this.settings.toData()
)

fun SetBio.toData(): TdApi.SetBio = TdApi.SetBio(
    this.bio
)

fun SetBirthdate.toData(): TdApi.SetBirthdate = TdApi.SetBirthdate(
    this.birthdate.toData()
)

fun SetBotInfoDescription.toData(): TdApi.SetBotInfoDescription = TdApi.SetBotInfoDescription(
    this.botUserId,
    this.languageCode,
    this.description
)

fun SetBotInfoShortDescription.toData(): TdApi.SetBotInfoShortDescription = TdApi.SetBotInfoShortDescription(
    this.botUserId,
    this.languageCode,
    this.shortDescription
)

fun SetBotProfilePhoto.toData(): TdApi.SetBotProfilePhoto = TdApi.SetBotProfilePhoto(
    this.botUserId,
    this.photo.toData()
)

fun SetBotUpdatesStatus.toData(): TdApi.SetBotUpdatesStatus = TdApi.SetBotUpdatesStatus(
    this.pendingUpdateCount,
    this.errorMessage
)

fun SetBusinessAccountBio.toData(): TdApi.SetBusinessAccountBio = TdApi.SetBusinessAccountBio(
    this.businessConnectionId,
    this.bio
)

fun SetBusinessAccountGiftSettings.toData(): TdApi.SetBusinessAccountGiftSettings = TdApi.SetBusinessAccountGiftSettings(
    this.businessConnectionId,
    this.settings.toData()
)

fun SetBusinessAccountName.toData(): TdApi.SetBusinessAccountName = TdApi.SetBusinessAccountName(
    this.businessConnectionId,
    this.firstName,
    this.lastName
)

fun SetBusinessAccountProfilePhoto.toData(): TdApi.SetBusinessAccountProfilePhoto = TdApi.SetBusinessAccountProfilePhoto(
    this.businessConnectionId,
    this.photo.toData(),
    this.isPublic
)

fun SetBusinessAccountUsername.toData(): TdApi.SetBusinessAccountUsername = TdApi.SetBusinessAccountUsername(
    this.businessConnectionId,
    this.username
)

fun SetBusinessAwayMessageSettings.toData(): TdApi.SetBusinessAwayMessageSettings = TdApi.SetBusinessAwayMessageSettings(
    this.awayMessageSettings.toData()
)

fun SetBusinessConnectedBot.toData(): TdApi.SetBusinessConnectedBot = TdApi.SetBusinessConnectedBot(
    this.bot.toData()
)

fun SetBusinessGreetingMessageSettings.toData(): TdApi.SetBusinessGreetingMessageSettings = TdApi.SetBusinessGreetingMessageSettings(
    this.greetingMessageSettings.toData()
)

fun SetBusinessLocation.toData(): TdApi.SetBusinessLocation = TdApi.SetBusinessLocation(
    this.location.toData()
)

fun SetBusinessMessageIsPinned.toData(): TdApi.SetBusinessMessageIsPinned = TdApi.SetBusinessMessageIsPinned(
    this.businessConnectionId,
    this.chatId,
    this.messageId,
    this.isPinned
)

fun SetBusinessOpeningHours.toData(): TdApi.SetBusinessOpeningHours = TdApi.SetBusinessOpeningHours(
    this.openingHours.toData()
)

fun SetBusinessStartPage.toData(): TdApi.SetBusinessStartPage = TdApi.SetBusinessStartPage(
    this.startPage.toData()
)

fun SetChatAccentColor.toData(): TdApi.SetChatAccentColor = TdApi.SetChatAccentColor(
    this.chatId,
    this.accentColorId,
    this.backgroundCustomEmojiId
)

fun SetChatActiveStoriesList.toData(): TdApi.SetChatActiveStoriesList = TdApi.SetChatActiveStoriesList(
    this.chatId,
    this.storyList.toData()
)

fun SetChatAffiliateProgram.toData(): TdApi.SetChatAffiliateProgram = TdApi.SetChatAffiliateProgram(
    this.chatId,
    this.parameters.toData()
)

fun SetChatBackground.toData(): TdApi.SetChatBackground = TdApi.SetChatBackground(
    this.chatId,
    this.background.toData(),
    this.type.toData(),
    this.darkThemeDimming,
    this.onlyForSelf
)

fun SetChatDirectMessagesGroup.toData(): TdApi.SetChatDirectMessagesGroup = TdApi.SetChatDirectMessagesGroup(
    this.chatId,
    this.isEnabled,
    this.paidMessageStarCount
)

fun SetChatEmojiStatus.toData(): TdApi.SetChatEmojiStatus = TdApi.SetChatEmojiStatus(
    this.chatId,
    this.emojiStatus.toData()
)

fun SetChatLocation.toData(): TdApi.SetChatLocation = TdApi.SetChatLocation(
    this.chatId,
    this.location.toData()
)

fun SetChatMemberStatus.toData(): TdApi.SetChatMemberStatus = TdApi.SetChatMemberStatus(
    this.chatId,
    this.memberId.toData(),
    this.status.toData()
)

fun SetChatMessageAutoDeleteTime.toData(): TdApi.SetChatMessageAutoDeleteTime = TdApi.SetChatMessageAutoDeleteTime(
    this.chatId,
    this.messageAutoDeleteTime
)

fun SetChatMessageSender.toData(): TdApi.SetChatMessageSender = TdApi.SetChatMessageSender(
    this.chatId,
    this.messageSenderId.toData()
)

fun SetChatPaidMessageStarCount.toData(): TdApi.SetChatPaidMessageStarCount = TdApi.SetChatPaidMessageStarCount(
    this.chatId,
    this.paidMessageStarCount
)

fun SetChatPinnedStories.toData(): TdApi.SetChatPinnedStories = TdApi.SetChatPinnedStories(
    this.chatId,
    this.storyIds
)

fun SetChatProfileAccentColor.toData(): TdApi.SetChatProfileAccentColor = TdApi.SetChatProfileAccentColor(
    this.chatId,
    this.profileAccentColorId,
    this.profileBackgroundCustomEmojiId
)

fun SetChatSlowModeDelay.toData(): TdApi.SetChatSlowModeDelay = TdApi.SetChatSlowModeDelay(
    this.chatId,
    this.slowModeDelay
)

fun SetChatTheme.toData(): TdApi.SetChatTheme = TdApi.SetChatTheme(
    this.chatId,
    this.theme.toData()
)

fun SetCloseFriends.toData(): TdApi.SetCloseFriends = TdApi.SetCloseFriends(
    this.userIds
)

fun SetCommands.toData(): TdApi.SetCommands = TdApi.SetCommands(
    this.scope.toData(),
    this.languageCode,
    this.commands.map { it.toData() }.toTypedArray()
)

fun SetCustomEmojiStickerSetThumbnail.toData(): TdApi.SetCustomEmojiStickerSetThumbnail = TdApi.SetCustomEmojiStickerSetThumbnail(
    this.name,
    this.customEmojiId
)

fun SetCustomLanguagePack.toData(): TdApi.SetCustomLanguagePack = TdApi.SetCustomLanguagePack(
    this.info.toData(),
    this.strings.map { it.toData() }.toTypedArray()
)

fun SetCustomLanguagePackString.toData(): TdApi.SetCustomLanguagePackString = TdApi.SetCustomLanguagePackString(
    this.languagePackId,
    this.newString.toData()
)

fun SetDatabaseEncryptionKey.toData(): TdApi.SetDatabaseEncryptionKey = TdApi.SetDatabaseEncryptionKey(
    this.newEncryptionKey
)

fun SetDefaultBackground.toData(): TdApi.SetDefaultBackground = TdApi.SetDefaultBackground(
    this.background.toData(),
    this.type.toData(),
    this.forDarkTheme
)

fun SetDefaultChannelAdministratorRights.toData(): TdApi.SetDefaultChannelAdministratorRights = TdApi.SetDefaultChannelAdministratorRights(
    this.defaultChannelAdministratorRights.toData()
)

fun SetDefaultGroupAdministratorRights.toData(): TdApi.SetDefaultGroupAdministratorRights = TdApi.SetDefaultGroupAdministratorRights(
    this.defaultGroupAdministratorRights.toData()
)

fun SetDefaultMessageAutoDeleteTime.toData(): TdApi.SetDefaultMessageAutoDeleteTime = TdApi.SetDefaultMessageAutoDeleteTime(
    this.messageAutoDeleteTime.toData()
)

fun SetDefaultReactionType.toData(): TdApi.SetDefaultReactionType = TdApi.SetDefaultReactionType(
    this.reactionType.toData()
)

fun SetDirectMessagesChatTopicDraftMessage.toData(): TdApi.SetDirectMessagesChatTopicDraftMessage = TdApi.SetDirectMessagesChatTopicDraftMessage(
    this.chatId,
    this.topicId,
    this.draftMessage.toData()
)

fun SetDirectMessagesChatTopicIsMarkedAsUnread.toData(): TdApi.SetDirectMessagesChatTopicIsMarkedAsUnread = TdApi.SetDirectMessagesChatTopicIsMarkedAsUnread(
    this.chatId,
    this.topicId,
    this.isMarkedAsUnread
)

fun SetEmojiStatus.toData(): TdApi.SetEmojiStatus = TdApi.SetEmojiStatus(
    this.emojiStatus.toData()
)

fun SetGameScore.toData(): TdApi.SetGameScore = TdApi.SetGameScore(
    this.chatId,
    this.messageId,
    this.editMessage,
    this.userId,
    this.score,
    this.force
)

fun SetGiftCollectionName.toData(): TdApi.SetGiftCollectionName = TdApi.SetGiftCollectionName(
    this.ownerId.toData(),
    this.collectionId,
    this.name
)

fun SetGiftResalePrice.toData(): TdApi.SetGiftResalePrice = TdApi.SetGiftResalePrice(
    this.receivedGiftId,
    this.price.toData()
)

fun SetGiftSettings.toData(): TdApi.SetGiftSettings = TdApi.SetGiftSettings(
    this.settings.toData()
)

fun SetGroupCallParticipantIsSpeaking.toData(): TdApi.SetGroupCallParticipantIsSpeaking = TdApi.SetGroupCallParticipantIsSpeaking(
    this.groupCallId,
    this.audioSource,
    this.isSpeaking
)

fun SetInactiveSessionTtl.toData(): TdApi.SetInactiveSessionTtl = TdApi.SetInactiveSessionTtl(
    this.inactiveSessionTtlDays
)

fun SetInlineGameScore.toData(): TdApi.SetInlineGameScore = TdApi.SetInlineGameScore(
    this.inlineMessageId,
    this.editMessage,
    this.userId,
    this.score,
    this.force
)

fun SetLoginEmailAddress.toData(): TdApi.SetLoginEmailAddress = TdApi.SetLoginEmailAddress(
    this.newLoginEmailAddress
)

fun SetMainProfileTab.toData(): TdApi.SetMainProfileTab = TdApi.SetMainProfileTab(
    this.mainProfileTab.toData()
)

fun SetMenuButton.toData(): TdApi.SetMenuButton = TdApi.SetMenuButton(
    this.userId,
    this.menuButton.toData()
)

fun SetMessageFactCheck.toData(): TdApi.SetMessageFactCheck = TdApi.SetMessageFactCheck(
    this.chatId,
    this.messageId,
    this.text.toData()
)

fun SetMessageReactions.toData(): TdApi.SetMessageReactions = TdApi.SetMessageReactions(
    this.chatId,
    this.messageId,
    this.reactionTypes.map { it.toData() }.toTypedArray(),
    this.isBig
)

fun SetMessageSenderBlockList.toData(): TdApi.SetMessageSenderBlockList = TdApi.SetMessageSenderBlockList(
    this.senderId.toData(),
    this.blockList.toData()
)

fun SetMessageSenderBotVerification.toData(): TdApi.SetMessageSenderBotVerification = TdApi.SetMessageSenderBotVerification(
    this.botUserId,
    this.verifiedId.toData(),
    this.customDescription
)

fun SetName.toData(): TdApi.SetName = TdApi.SetName(
    this.firstName,
    this.lastName
)

fun SetNetworkType.toData(): TdApi.SetNetworkType = TdApi.SetNetworkType(
    this.type.toData()
)

fun SetNewChatPrivacySettings.toData(): TdApi.SetNewChatPrivacySettings = TdApi.SetNewChatPrivacySettings(
    this.settings.toData()
)

fun SetPaidMessageReactionType.toData(): TdApi.SetPaidMessageReactionType = TdApi.SetPaidMessageReactionType(
    this.chatId,
    this.messageId,
    this.type.toData()
)

fun SetPassword.toData(): TdApi.SetPassword = TdApi.SetPassword(
    this.oldPassword,
    this.newPassword,
    this.newHint,
    this.setRecoveryEmailAddress,
    this.newRecoveryEmailAddress
)

fun SetPersonalChat.toData(): TdApi.SetPersonalChat = TdApi.SetPersonalChat(
    this.chatId
)

fun SetPinnedChats.toData(): TdApi.SetPinnedChats = TdApi.SetPinnedChats(
    this.chatList.toData(),
    this.chatIds
)

fun SetPinnedForumTopics.toData(): TdApi.SetPinnedForumTopics = TdApi.SetPinnedForumTopics(
    this.chatId,
    this.messageThreadIds
)

fun SetPinnedGifts.toData(): TdApi.SetPinnedGifts = TdApi.SetPinnedGifts(
    this.ownerId.toData(),
    this.receivedGiftIds.toTypedArray()
)

fun SetPinnedSavedMessagesTopics.toData(): TdApi.SetPinnedSavedMessagesTopics = TdApi.SetPinnedSavedMessagesTopics(
    this.savedMessagesTopicIds
)

fun SetPollAnswer.toData(): TdApi.SetPollAnswer = TdApi.SetPollAnswer(
    this.chatId,
    this.messageId,
    this.optionIds
)

fun SetProfileAccentColor.toData(): TdApi.SetProfileAccentColor = TdApi.SetProfileAccentColor(
    this.profileAccentColorId,
    this.profileBackgroundCustomEmojiId
)

fun SetProfileAudioPosition.toData(): TdApi.SetProfileAudioPosition = TdApi.SetProfileAudioPosition(
    this.fileId,
    this.afterFileId
)

fun SetProfilePhoto.toData(): TdApi.SetProfilePhoto = TdApi.SetProfilePhoto(
    this.photo.toData(),
    this.isPublic
)

fun SetQuickReplyShortcutName.toData(): TdApi.SetQuickReplyShortcutName = TdApi.SetQuickReplyShortcutName(
    this.shortcutId,
    this.name
)

fun SetReactionNotificationSettings.toData(): TdApi.SetReactionNotificationSettings = TdApi.SetReactionNotificationSettings(
    this.notificationSettings.toData()
)

fun SetReadDatePrivacySettings.toData(): TdApi.SetReadDatePrivacySettings = TdApi.SetReadDatePrivacySettings(
    this.settings.toData()
)

fun SetRecoveryEmailAddress.toData(): TdApi.SetRecoveryEmailAddress = TdApi.SetRecoveryEmailAddress(
    this.password,
    this.newRecoveryEmailAddress
)

fun SetSavedMessagesTagLabel.toData(): TdApi.SetSavedMessagesTagLabel = TdApi.SetSavedMessagesTagLabel(
    this.tag.toData(),
    this.label
)

fun SetScopeNotificationSettings.toData(): TdApi.SetScopeNotificationSettings = TdApi.SetScopeNotificationSettings(
    this.scope.toData(),
    this.notificationSettings.toData()
)

fun SetStickerEmojis.toData(): TdApi.SetStickerEmojis = TdApi.SetStickerEmojis(
    this.sticker.toData(),
    this.emojis
)

fun SetStickerKeywords.toData(): TdApi.SetStickerKeywords = TdApi.SetStickerKeywords(
    this.sticker.toData(),
    this.keywords.toTypedArray()
)

fun SetStickerMaskPosition.toData(): TdApi.SetStickerMaskPosition = TdApi.SetStickerMaskPosition(
    this.sticker.toData(),
    this.maskPosition.toData()
)

fun SetStickerPositionInSet.toData(): TdApi.SetStickerPositionInSet = TdApi.SetStickerPositionInSet(
    this.sticker.toData(),
    this.position
)

fun SetStickerSetThumbnail.toData(): TdApi.SetStickerSetThumbnail = TdApi.SetStickerSetThumbnail(
    this.userId,
    this.name,
    this.thumbnail.toData(),
    this.format.toData()
)

fun SetStickerSetTitle.toData(): TdApi.SetStickerSetTitle = TdApi.SetStickerSetTitle(
    this.name,
    this.title
)

fun SetStoryAlbumName.toData(): TdApi.SetStoryAlbumName = TdApi.SetStoryAlbumName(
    this.chatId,
    this.storyAlbumId,
    this.name
)

fun SetStoryPrivacySettings.toData(): TdApi.SetStoryPrivacySettings = TdApi.SetStoryPrivacySettings(
    this.storyId,
    this.privacySettings.toData()
)

fun SetSupergroupCustomEmojiStickerSet.toData(): TdApi.SetSupergroupCustomEmojiStickerSet = TdApi.SetSupergroupCustomEmojiStickerSet(
    this.supergroupId,
    this.customEmojiStickerSetId
)

fun SetSupergroupMainProfileTab.toData(): TdApi.SetSupergroupMainProfileTab = TdApi.SetSupergroupMainProfileTab(
    this.supergroupId,
    this.mainProfileTab.toData()
)

fun SetSupergroupStickerSet.toData(): TdApi.SetSupergroupStickerSet = TdApi.SetSupergroupStickerSet(
    this.supergroupId,
    this.stickerSetId
)

fun SetSupergroupUnrestrictBoostCount.toData(): TdApi.SetSupergroupUnrestrictBoostCount = TdApi.SetSupergroupUnrestrictBoostCount(
    this.supergroupId,
    this.unrestrictBoostCount
)

fun SetSupergroupUsername.toData(): TdApi.SetSupergroupUsername = TdApi.SetSupergroupUsername(
    this.supergroupId,
    this.username
)

fun SetUserEmojiStatus.toData(): TdApi.SetUserEmojiStatus = TdApi.SetUserEmojiStatus(
    this.userId,
    this.emojiStatus.toData()
)

fun SetUserPersonalProfilePhoto.toData(): TdApi.SetUserPersonalProfilePhoto = TdApi.SetUserPersonalProfilePhoto(
    this.userId,
    this.photo.toData()
)

fun SetUserSupportInfo.toData(): TdApi.SetUserSupportInfo = TdApi.SetUserSupportInfo(
    this.userId,
    this.message.toData()
)

fun SetUsername.toData(): TdApi.SetUsername = TdApi.SetUsername(
    this.username
)

fun SetVideoChatDefaultParticipant.toData(): TdApi.SetVideoChatDefaultParticipant = TdApi.SetVideoChatDefaultParticipant(
    this.chatId,
    this.defaultParticipantId.toData()
)

fun SetVideoChatTitle.toData(): TdApi.SetVideoChatTitle = TdApi.SetVideoChatTitle(
    this.groupCallId,
    this.title
)

