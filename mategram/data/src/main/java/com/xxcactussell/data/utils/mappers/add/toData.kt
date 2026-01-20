package com.xxcactussell.data.utils.mappers.add

import com.xxcactussell.data.utils.mappers.chat.toData
import com.xxcactussell.data.utils.mappers.infrastructure.toData
import com.xxcactussell.data.utils.mappers.input.toData
import com.xxcactussell.data.utils.mappers.message.toData
import com.xxcactussell.data.utils.mappers.paid.toData
import com.xxcactussell.data.utils.mappers.reaction.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun AddBotMediaPreview.toData(): TdApi.AddBotMediaPreview = TdApi.AddBotMediaPreview(
    this.botUserId,
    this.languageCode,
    this.content.toData()
)

fun AddChatFolderByInviteLink.toData(): TdApi.AddChatFolderByInviteLink = TdApi.AddChatFolderByInviteLink(
    this.inviteLink,
    this.chatIds
)

fun AddChatMember.toData(): TdApi.AddChatMember = TdApi.AddChatMember(
    this.chatId,
    this.userId,
    this.forwardLimit
)

fun AddChatMembers.toData(): TdApi.AddChatMembers = TdApi.AddChatMembers(
    this.chatId,
    this.userIds
)

fun AddChatToList.toData(): TdApi.AddChatToList = TdApi.AddChatToList(
    this.chatId,
    this.chatList.toData()
)

fun AddChecklistTasks.toData(): TdApi.AddChecklistTasks = TdApi.AddChecklistTasks(
    this.chatId,
    this.messageId,
    this.tasks.map { it.toData() }.toTypedArray()
)

fun AddCustomServerLanguagePack.toData(): TdApi.AddCustomServerLanguagePack = TdApi.AddCustomServerLanguagePack(
    this.languagePackId
)

fun AddFavoriteSticker.toData(): TdApi.AddFavoriteSticker = TdApi.AddFavoriteSticker(
    this.sticker.toData()
)

fun AddFileToDownloads.toData(): TdApi.AddFileToDownloads = TdApi.AddFileToDownloads(
    this.fileId,
    this.chatId,
    this.messageId,
    this.priority
)

fun AddGiftCollectionGifts.toData(): TdApi.AddGiftCollectionGifts = TdApi.AddGiftCollectionGifts(
    this.ownerId.toData(),
    this.collectionId,
    this.receivedGiftIds.toTypedArray()
)

fun AddLocalMessage.toData(): TdApi.AddLocalMessage = TdApi.AddLocalMessage(
    this.chatId,
    this.senderId.toData(),
    this.replyTo.toData(),
    this.disableNotification,
    this.inputMessageContent.toData()
)

fun AddLogMessage.toData(): TdApi.AddLogMessage = TdApi.AddLogMessage(
    this.verbosityLevel,
    this.text
)

fun AddMessageReaction.toData(): TdApi.AddMessageReaction = TdApi.AddMessageReaction(
    this.chatId,
    this.messageId,
    this.reactionType.toData(),
    this.isBig,
    this.updateRecentReactions
)

fun AddNetworkStatistics.toData(): TdApi.AddNetworkStatistics = TdApi.AddNetworkStatistics(
    this.entry.toData()
)

fun AddOffer.toData(): TdApi.AddOffer = TdApi.AddOffer(
    this.chatId,
    this.messageId,
    this.options.toData()
)

fun AddPendingPaidMessageReaction.toData(): TdApi.AddPendingPaidMessageReaction = TdApi.AddPendingPaidMessageReaction(
    this.chatId,
    this.messageId,
    this.starCount,
    this.type.toData()
)

fun AddProfileAudio.toData(): TdApi.AddProfileAudio = TdApi.AddProfileAudio(
    this.fileId
)

fun AddQuickReplyShortcutInlineQueryResultMessage.toData(): TdApi.AddQuickReplyShortcutInlineQueryResultMessage = TdApi.AddQuickReplyShortcutInlineQueryResultMessage(
    this.shortcutName,
    this.replyToMessageId,
    this.queryId,
    this.resultId,
    this.hideViaBot
)

fun AddQuickReplyShortcutMessage.toData(): TdApi.AddQuickReplyShortcutMessage = TdApi.AddQuickReplyShortcutMessage(
    this.shortcutName,
    this.replyToMessageId,
    this.inputMessageContent.toData()
)

fun AddQuickReplyShortcutMessageAlbum.toData(): TdApi.AddQuickReplyShortcutMessageAlbum = TdApi.AddQuickReplyShortcutMessageAlbum(
    this.shortcutName,
    this.replyToMessageId,
    this.inputMessageContents.map { it.toData() }.toTypedArray()
)

fun AddRecentSticker.toData(): TdApi.AddRecentSticker = TdApi.AddRecentSticker(
    this.isAttached,
    this.sticker.toData()
)

fun AddRecentlyFoundChat.toData(): TdApi.AddRecentlyFoundChat = TdApi.AddRecentlyFoundChat(
    this.chatId
)

fun AddSavedAnimation.toData(): TdApi.AddSavedAnimation = TdApi.AddSavedAnimation(
    this.animation.toData()
)

fun AddSavedNotificationSound.toData(): TdApi.AddSavedNotificationSound = TdApi.AddSavedNotificationSound(
    this.sound.toData()
)

fun AddStickerToSet.toData(): TdApi.AddStickerToSet = TdApi.AddStickerToSet(
    this.userId,
    this.name,
    this.sticker.toData()
)

fun AddStoryAlbumStories.toData(): TdApi.AddStoryAlbumStories = TdApi.AddStoryAlbumStories(
    this.chatId,
    this.storyAlbumId,
    this.storyIds
)

