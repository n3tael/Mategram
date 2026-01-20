package com.xxcactussell.data.utils.mappers.remove

import com.xxcactussell.data.utils.mappers.input.toData
import com.xxcactussell.data.utils.mappers.message.toData
import com.xxcactussell.data.utils.mappers.reaction.toData
import com.xxcactussell.data.utils.mappers.top.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun RemoveAllFilesFromDownloads.toData(): TdApi.RemoveAllFilesFromDownloads = TdApi.RemoveAllFilesFromDownloads(
    this.onlyActive,
    this.onlyCompleted,
    this.deleteFromCache
)

fun RemoveBusinessConnectedBotFromChat.toData(): TdApi.RemoveBusinessConnectedBotFromChat = TdApi.RemoveBusinessConnectedBotFromChat(
    this.chatId
)

fun RemoveChatActionBar.toData(): TdApi.RemoveChatActionBar = TdApi.RemoveChatActionBar(
    this.chatId
)

fun RemoveFavoriteSticker.toData(): TdApi.RemoveFavoriteSticker = TdApi.RemoveFavoriteSticker(
    this.sticker.toData()
)

fun RemoveFileFromDownloads.toData(): TdApi.RemoveFileFromDownloads = TdApi.RemoveFileFromDownloads(
    this.fileId,
    this.deleteFromCache
)

fun RemoveGiftCollectionGifts.toData(): TdApi.RemoveGiftCollectionGifts = TdApi.RemoveGiftCollectionGifts(
    this.ownerId.toData(),
    this.collectionId,
    this.receivedGiftIds.toTypedArray()
)

fun RemoveInstalledBackground.toData(): TdApi.RemoveInstalledBackground = TdApi.RemoveInstalledBackground(
    this.backgroundId
)

fun RemoveMessageReaction.toData(): TdApi.RemoveMessageReaction = TdApi.RemoveMessageReaction(
    this.chatId,
    this.messageId,
    this.reactionType.toData()
)

fun RemoveMessageSenderBotVerification.toData(): TdApi.RemoveMessageSenderBotVerification = TdApi.RemoveMessageSenderBotVerification(
    this.botUserId,
    this.verifiedId.toData()
)

fun RemoveNotification.toData(): TdApi.RemoveNotification = TdApi.RemoveNotification(
    this.notificationGroupId,
    this.notificationId
)

fun RemoveNotificationGroup.toData(): TdApi.RemoveNotificationGroup = TdApi.RemoveNotificationGroup(
    this.notificationGroupId,
    this.maxNotificationId
)

fun RemovePendingPaidMessageReactions.toData(): TdApi.RemovePendingPaidMessageReactions = TdApi.RemovePendingPaidMessageReactions(
    this.chatId,
    this.messageId
)

fun RemoveProfileAudio.toData(): TdApi.RemoveProfileAudio = TdApi.RemoveProfileAudio(
    this.fileId
)

fun RemoveRecentHashtag.toData(): TdApi.RemoveRecentHashtag = TdApi.RemoveRecentHashtag(
    this.hashtag
)

fun RemoveRecentSticker.toData(): TdApi.RemoveRecentSticker = TdApi.RemoveRecentSticker(
    this.isAttached,
    this.sticker.toData()
)

fun RemoveRecentlyFoundChat.toData(): TdApi.RemoveRecentlyFoundChat = TdApi.RemoveRecentlyFoundChat(
    this.chatId
)

fun RemoveSavedAnimation.toData(): TdApi.RemoveSavedAnimation = TdApi.RemoveSavedAnimation(
    this.animation.toData()
)

fun RemoveSavedNotificationSound.toData(): TdApi.RemoveSavedNotificationSound = TdApi.RemoveSavedNotificationSound(
    this.notificationSoundId
)

fun RemoveSearchedForTag.toData(): TdApi.RemoveSearchedForTag = TdApi.RemoveSearchedForTag(
    this.tag
)

fun RemoveStickerFromSet.toData(): TdApi.RemoveStickerFromSet = TdApi.RemoveStickerFromSet(
    this.sticker.toData()
)

fun RemoveStoryAlbumStories.toData(): TdApi.RemoveStoryAlbumStories = TdApi.RemoveStoryAlbumStories(
    this.chatId,
    this.storyAlbumId,
    this.storyIds
)

fun RemoveTopChat.toData(): TdApi.RemoveTopChat = TdApi.RemoveTopChat(
    this.category.toData(),
    this.chatId
)

