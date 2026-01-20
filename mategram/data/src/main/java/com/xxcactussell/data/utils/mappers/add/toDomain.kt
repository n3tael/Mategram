package com.xxcactussell.data.utils.mappers.add

import com.xxcactussell.data.utils.mappers.chat.toDomain
import com.xxcactussell.data.utils.mappers.infrastructure.toDomain
import com.xxcactussell.data.utils.mappers.input.toDomain
import com.xxcactussell.data.utils.mappers.message.toDomain
import com.xxcactussell.data.utils.mappers.paid.toDomain
import com.xxcactussell.data.utils.mappers.reaction.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.AddBotMediaPreview.toDomain(): AddBotMediaPreview = AddBotMediaPreview(
    botUserId = this.botUserId,
    languageCode = this.languageCode,
    content = this.content.toDomain()
)

fun TdApi.AddChatFolderByInviteLink.toDomain(): AddChatFolderByInviteLink = AddChatFolderByInviteLink(
    inviteLink = this.inviteLink,
    chatIds = this.chatIds
)

fun TdApi.AddChatMember.toDomain(): AddChatMember = AddChatMember(
    chatId = this.chatId,
    userId = this.userId,
    forwardLimit = this.forwardLimit
)

fun TdApi.AddChatMembers.toDomain(): AddChatMembers = AddChatMembers(
    chatId = this.chatId,
    userIds = this.userIds
)

fun TdApi.AddChatToList.toDomain(): AddChatToList = AddChatToList(
    chatId = this.chatId,
    chatList = this.chatList.toDomain()
)

fun TdApi.AddChecklistTasks.toDomain(): AddChecklistTasks = AddChecklistTasks(
    chatId = this.chatId,
    messageId = this.messageId,
    tasks = this.tasks.map { it.toDomain() }
)

fun TdApi.AddCustomServerLanguagePack.toDomain(): AddCustomServerLanguagePack = AddCustomServerLanguagePack(
    languagePackId = this.languagePackId
)

fun TdApi.AddFavoriteSticker.toDomain(): AddFavoriteSticker = AddFavoriteSticker(
    sticker = this.sticker.toDomain()
)

fun TdApi.AddFileToDownloads.toDomain(): AddFileToDownloads = AddFileToDownloads(
    fileId = this.fileId,
    chatId = this.chatId,
    messageId = this.messageId,
    priority = this.priority
)

fun TdApi.AddGiftCollectionGifts.toDomain(): AddGiftCollectionGifts = AddGiftCollectionGifts(
    ownerId = this.ownerId.toDomain(),
    collectionId = this.collectionId,
    receivedGiftIds = this.receivedGiftIds.toList()
)

fun TdApi.AddLocalMessage.toDomain(): AddLocalMessage = AddLocalMessage(
    chatId = this.chatId,
    senderId = this.senderId.toDomain(),
    replyTo = this.replyTo.toDomain(),
    disableNotification = this.disableNotification,
    inputMessageContent = this.inputMessageContent.toDomain()
)

fun TdApi.AddLogMessage.toDomain(): AddLogMessage = AddLogMessage(
    verbosityLevel = this.verbosityLevel,
    text = this.text
)

fun TdApi.AddMessageReaction.toDomain(): AddMessageReaction = AddMessageReaction(
    chatId = this.chatId,
    messageId = this.messageId,
    reactionType = this.reactionType.toDomain(),
    isBig = this.isBig,
    updateRecentReactions = this.updateRecentReactions
)

fun TdApi.AddNetworkStatistics.toDomain(): AddNetworkStatistics = AddNetworkStatistics(
    entry = this.entry.toDomain()
)

fun TdApi.AddOffer.toDomain(): AddOffer = AddOffer(
    chatId = this.chatId,
    messageId = this.messageId,
    options = this.options.toDomain()
)

fun TdApi.AddPendingPaidMessageReaction.toDomain(): AddPendingPaidMessageReaction = AddPendingPaidMessageReaction(
    chatId = this.chatId,
    messageId = this.messageId,
    starCount = this.starCount,
    type = this.type.toDomain()
)

fun TdApi.AddProfileAudio.toDomain(): AddProfileAudio = AddProfileAudio(
    fileId = this.fileId
)

fun TdApi.AddQuickReplyShortcutInlineQueryResultMessage.toDomain(): AddQuickReplyShortcutInlineQueryResultMessage = AddQuickReplyShortcutInlineQueryResultMessage(
    shortcutName = this.shortcutName,
    replyToMessageId = this.replyToMessageId,
    queryId = this.queryId,
    resultId = this.resultId,
    hideViaBot = this.hideViaBot
)

fun TdApi.AddQuickReplyShortcutMessage.toDomain(): AddQuickReplyShortcutMessage = AddQuickReplyShortcutMessage(
    shortcutName = this.shortcutName,
    replyToMessageId = this.replyToMessageId,
    inputMessageContent = this.inputMessageContent.toDomain()
)

fun TdApi.AddQuickReplyShortcutMessageAlbum.toDomain(): AddQuickReplyShortcutMessageAlbum = AddQuickReplyShortcutMessageAlbum(
    shortcutName = this.shortcutName,
    replyToMessageId = this.replyToMessageId,
    inputMessageContents = this.inputMessageContents.map { it.toDomain() }
)

fun TdApi.AddRecentSticker.toDomain(): AddRecentSticker = AddRecentSticker(
    isAttached = this.isAttached,
    sticker = this.sticker.toDomain()
)

fun TdApi.AddRecentlyFoundChat.toDomain(): AddRecentlyFoundChat = AddRecentlyFoundChat(
    chatId = this.chatId
)

fun TdApi.AddSavedAnimation.toDomain(): AddSavedAnimation = AddSavedAnimation(
    animation = this.animation.toDomain()
)

fun TdApi.AddSavedNotificationSound.toDomain(): AddSavedNotificationSound = AddSavedNotificationSound(
    sound = this.sound.toDomain()
)

fun TdApi.AddStickerToSet.toDomain(): AddStickerToSet = AddStickerToSet(
    userId = this.userId,
    name = this.name,
    sticker = this.sticker.toDomain()
)

fun TdApi.AddStoryAlbumStories.toDomain(): AddStoryAlbumStories = AddStoryAlbumStories(
    chatId = this.chatId,
    storyAlbumId = this.storyAlbumId,
    storyIds = this.storyIds
)

