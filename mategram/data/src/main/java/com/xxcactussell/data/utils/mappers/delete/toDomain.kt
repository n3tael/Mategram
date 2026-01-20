package com.xxcactussell.data.utils.mappers.delete

import com.xxcactussell.data.utils.mappers.bots.toDomain
import com.xxcactussell.data.utils.mappers.message.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.DeleteAccount.toDomain(): DeleteAccount = DeleteAccount(
    reason = this.reason,
    password = this.password
)

fun TdApi.DeleteAllCallMessages.toDomain(): DeleteAllCallMessages = DeleteAllCallMessages(
    revoke = this.revoke
)

fun TdApi.DeleteAllRevokedChatInviteLinks.toDomain(): DeleteAllRevokedChatInviteLinks = DeleteAllRevokedChatInviteLinks(
    chatId = this.chatId,
    creatorUserId = this.creatorUserId
)

fun TdApi.DeleteBotMediaPreviews.toDomain(): DeleteBotMediaPreviews = DeleteBotMediaPreviews(
    botUserId = this.botUserId,
    languageCode = this.languageCode,
    fileIds = this.fileIds
)

fun TdApi.DeleteBusinessChatLink.toDomain(): DeleteBusinessChatLink = DeleteBusinessChatLink(
    link = this.link
)

fun TdApi.DeleteBusinessConnectedBot.toDomain(): DeleteBusinessConnectedBot = DeleteBusinessConnectedBot(
    botUserId = this.botUserId
)

fun TdApi.DeleteBusinessStory.toDomain(): DeleteBusinessStory = DeleteBusinessStory(
    businessConnectionId = this.businessConnectionId,
    storyId = this.storyId
)

fun TdApi.DeleteCommands.toDomain(): DeleteCommands = DeleteCommands(
    scope = this.scope.toDomain(),
    languageCode = this.languageCode
)

fun TdApi.DeleteDefaultBackground.toDomain(): DeleteDefaultBackground = DeleteDefaultBackground(
    forDarkTheme = this.forDarkTheme
)

fun TdApi.DeleteDirectMessagesChatTopicHistory.toDomain(): DeleteDirectMessagesChatTopicHistory = DeleteDirectMessagesChatTopicHistory(
    chatId = this.chatId,
    topicId = this.topicId
)

fun TdApi.DeleteDirectMessagesChatTopicMessagesByDate.toDomain(): DeleteDirectMessagesChatTopicMessagesByDate = DeleteDirectMessagesChatTopicMessagesByDate(
    chatId = this.chatId,
    topicId = this.topicId,
    minDate = this.minDate,
    maxDate = this.maxDate
)

fun TdApi.DeleteGiftCollection.toDomain(): DeleteGiftCollection = DeleteGiftCollection(
    ownerId = this.ownerId.toDomain(),
    collectionId = this.collectionId
)

fun TdApi.DeleteLanguagePack.toDomain(): DeleteLanguagePack = DeleteLanguagePack(
    languagePackId = this.languagePackId
)

fun TdApi.DeleteProfilePhoto.toDomain(): DeleteProfilePhoto = DeleteProfilePhoto(
    profilePhotoId = this.profilePhotoId
)

fun TdApi.DeleteQuickReplyShortcut.toDomain(): DeleteQuickReplyShortcut = DeleteQuickReplyShortcut(
    shortcutId = this.shortcutId
)

fun TdApi.DeleteQuickReplyShortcutMessages.toDomain(): DeleteQuickReplyShortcutMessages = DeleteQuickReplyShortcutMessages(
    shortcutId = this.shortcutId,
    messageIds = this.messageIds
)

fun TdApi.DeleteRevokedChatInviteLink.toDomain(): DeleteRevokedChatInviteLink = DeleteRevokedChatInviteLink(
    chatId = this.chatId,
    inviteLink = this.inviteLink
)

fun TdApi.DeleteSavedMessagesTopicHistory.toDomain(): DeleteSavedMessagesTopicHistory = DeleteSavedMessagesTopicHistory(
    savedMessagesTopicId = this.savedMessagesTopicId
)

fun TdApi.DeleteSavedMessagesTopicMessagesByDate.toDomain(): DeleteSavedMessagesTopicMessagesByDate = DeleteSavedMessagesTopicMessagesByDate(
    savedMessagesTopicId = this.savedMessagesTopicId,
    minDate = this.minDate,
    maxDate = this.maxDate
)

fun TdApi.DeleteStickerSet.toDomain(): DeleteStickerSet = DeleteStickerSet(
    name = this.name
)

