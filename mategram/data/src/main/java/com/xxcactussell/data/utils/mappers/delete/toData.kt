package com.xxcactussell.data.utils.mappers.delete

import com.xxcactussell.data.utils.mappers.bots.toData
import com.xxcactussell.data.utils.mappers.message.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun DeleteAccount.toData(): TdApi.DeleteAccount = TdApi.DeleteAccount(
    this.reason,
    this.password
)

fun DeleteAllCallMessages.toData(): TdApi.DeleteAllCallMessages = TdApi.DeleteAllCallMessages(
    this.revoke
)

fun DeleteAllRevokedChatInviteLinks.toData(): TdApi.DeleteAllRevokedChatInviteLinks = TdApi.DeleteAllRevokedChatInviteLinks(
    this.chatId,
    this.creatorUserId
)

fun DeleteBotMediaPreviews.toData(): TdApi.DeleteBotMediaPreviews = TdApi.DeleteBotMediaPreviews(
    this.botUserId,
    this.languageCode,
    this.fileIds
)

fun DeleteBusinessChatLink.toData(): TdApi.DeleteBusinessChatLink = TdApi.DeleteBusinessChatLink(
    this.link
)

fun DeleteBusinessConnectedBot.toData(): TdApi.DeleteBusinessConnectedBot = TdApi.DeleteBusinessConnectedBot(
    this.botUserId
)

fun DeleteBusinessStory.toData(): TdApi.DeleteBusinessStory = TdApi.DeleteBusinessStory(
    this.businessConnectionId,
    this.storyId
)

fun DeleteCommands.toData(): TdApi.DeleteCommands = TdApi.DeleteCommands(
    this.scope.toData(),
    this.languageCode
)

fun DeleteDefaultBackground.toData(): TdApi.DeleteDefaultBackground = TdApi.DeleteDefaultBackground(
    this.forDarkTheme
)

fun DeleteDirectMessagesChatTopicHistory.toData(): TdApi.DeleteDirectMessagesChatTopicHistory = TdApi.DeleteDirectMessagesChatTopicHistory(
    this.chatId,
    this.topicId
)

fun DeleteDirectMessagesChatTopicMessagesByDate.toData(): TdApi.DeleteDirectMessagesChatTopicMessagesByDate = TdApi.DeleteDirectMessagesChatTopicMessagesByDate(
    this.chatId,
    this.topicId,
    this.minDate,
    this.maxDate
)

fun DeleteGiftCollection.toData(): TdApi.DeleteGiftCollection = TdApi.DeleteGiftCollection(
    this.ownerId.toData(),
    this.collectionId
)

fun DeleteLanguagePack.toData(): TdApi.DeleteLanguagePack = TdApi.DeleteLanguagePack(
    this.languagePackId
)

fun DeleteProfilePhoto.toData(): TdApi.DeleteProfilePhoto = TdApi.DeleteProfilePhoto(
    this.profilePhotoId
)

fun DeleteQuickReplyShortcut.toData(): TdApi.DeleteQuickReplyShortcut = TdApi.DeleteQuickReplyShortcut(
    this.shortcutId
)

fun DeleteQuickReplyShortcutMessages.toData(): TdApi.DeleteQuickReplyShortcutMessages = TdApi.DeleteQuickReplyShortcutMessages(
    this.shortcutId,
    this.messageIds
)

fun DeleteRevokedChatInviteLink.toData(): TdApi.DeleteRevokedChatInviteLink = TdApi.DeleteRevokedChatInviteLink(
    this.chatId,
    this.inviteLink
)

fun DeleteSavedMessagesTopicHistory.toData(): TdApi.DeleteSavedMessagesTopicHistory = TdApi.DeleteSavedMessagesTopicHistory(
    this.savedMessagesTopicId
)

fun DeleteSavedMessagesTopicMessagesByDate.toData(): TdApi.DeleteSavedMessagesTopicMessagesByDate = TdApi.DeleteSavedMessagesTopicMessagesByDate(
    this.savedMessagesTopicId,
    this.minDate,
    this.maxDate
)

fun DeleteStickerSet.toData(): TdApi.DeleteStickerSet = TdApi.DeleteStickerSet(
    this.name
)

