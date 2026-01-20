package com.xxcactussell.data.utils.mappers.remove

import com.xxcactussell.data.utils.mappers.input.toDomain
import com.xxcactussell.data.utils.mappers.message.toDomain
import com.xxcactussell.data.utils.mappers.reaction.toDomain
import com.xxcactussell.data.utils.mappers.top.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.RemoveAllFilesFromDownloads.toDomain(): RemoveAllFilesFromDownloads = RemoveAllFilesFromDownloads(
    onlyActive = this.onlyActive,
    onlyCompleted = this.onlyCompleted,
    deleteFromCache = this.deleteFromCache
)

fun TdApi.RemoveBusinessConnectedBotFromChat.toDomain(): RemoveBusinessConnectedBotFromChat = RemoveBusinessConnectedBotFromChat(
    chatId = this.chatId
)

fun TdApi.RemoveChatActionBar.toDomain(): RemoveChatActionBar = RemoveChatActionBar(
    chatId = this.chatId
)

fun TdApi.RemoveFavoriteSticker.toDomain(): RemoveFavoriteSticker = RemoveFavoriteSticker(
    sticker = this.sticker.toDomain()
)

fun TdApi.RemoveFileFromDownloads.toDomain(): RemoveFileFromDownloads = RemoveFileFromDownloads(
    fileId = this.fileId,
    deleteFromCache = this.deleteFromCache
)

fun TdApi.RemoveGiftCollectionGifts.toDomain(): RemoveGiftCollectionGifts = RemoveGiftCollectionGifts(
    ownerId = this.ownerId.toDomain(),
    collectionId = this.collectionId,
    receivedGiftIds = this.receivedGiftIds.toList()
)

fun TdApi.RemoveInstalledBackground.toDomain(): RemoveInstalledBackground = RemoveInstalledBackground(
    backgroundId = this.backgroundId
)

fun TdApi.RemoveMessageReaction.toDomain(): RemoveMessageReaction = RemoveMessageReaction(
    chatId = this.chatId,
    messageId = this.messageId,
    reactionType = this.reactionType.toDomain()
)

fun TdApi.RemoveMessageSenderBotVerification.toDomain(): RemoveMessageSenderBotVerification = RemoveMessageSenderBotVerification(
    botUserId = this.botUserId,
    verifiedId = this.verifiedId.toDomain()
)

fun TdApi.RemoveNotification.toDomain(): RemoveNotification = RemoveNotification(
    notificationGroupId = this.notificationGroupId,
    notificationId = this.notificationId
)

fun TdApi.RemoveNotificationGroup.toDomain(): RemoveNotificationGroup = RemoveNotificationGroup(
    notificationGroupId = this.notificationGroupId,
    maxNotificationId = this.maxNotificationId
)

fun TdApi.RemovePendingPaidMessageReactions.toDomain(): RemovePendingPaidMessageReactions = RemovePendingPaidMessageReactions(
    chatId = this.chatId,
    messageId = this.messageId
)

fun TdApi.RemoveProfileAudio.toDomain(): RemoveProfileAudio = RemoveProfileAudio(
    fileId = this.fileId
)

fun TdApi.RemoveRecentHashtag.toDomain(): RemoveRecentHashtag = RemoveRecentHashtag(
    hashtag = this.hashtag
)

fun TdApi.RemoveRecentSticker.toDomain(): RemoveRecentSticker = RemoveRecentSticker(
    isAttached = this.isAttached,
    sticker = this.sticker.toDomain()
)

fun TdApi.RemoveRecentlyFoundChat.toDomain(): RemoveRecentlyFoundChat = RemoveRecentlyFoundChat(
    chatId = this.chatId
)

fun TdApi.RemoveSavedAnimation.toDomain(): RemoveSavedAnimation = RemoveSavedAnimation(
    animation = this.animation.toDomain()
)

fun TdApi.RemoveSavedNotificationSound.toDomain(): RemoveSavedNotificationSound = RemoveSavedNotificationSound(
    notificationSoundId = this.notificationSoundId
)

fun TdApi.RemoveSearchedForTag.toDomain(): RemoveSearchedForTag = RemoveSearchedForTag(
    tag = this.tag
)

fun TdApi.RemoveStickerFromSet.toDomain(): RemoveStickerFromSet = RemoveStickerFromSet(
    sticker = this.sticker.toDomain()
)

fun TdApi.RemoveStoryAlbumStories.toDomain(): RemoveStoryAlbumStories = RemoveStoryAlbumStories(
    chatId = this.chatId,
    storyAlbumId = this.storyAlbumId,
    storyIds = this.storyIds
)

fun TdApi.RemoveTopChat.toDomain(): RemoveTopChat = RemoveTopChat(
    category = this.category.toDomain(),
    chatId = this.chatId
)

