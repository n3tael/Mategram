package com.xxcactussell.data.utils.mappers.reorder

import com.xxcactussell.data.utils.mappers.message.toDomain
import com.xxcactussell.data.utils.mappers.sticker.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.ReorderActiveUsernames.toDomain(): ReorderActiveUsernames = ReorderActiveUsernames(
    usernames = this.usernames.toList()
)

fun TdApi.ReorderBotActiveUsernames.toDomain(): ReorderBotActiveUsernames = ReorderBotActiveUsernames(
    botUserId = this.botUserId,
    usernames = this.usernames.toList()
)

fun TdApi.ReorderBotMediaPreviews.toDomain(): ReorderBotMediaPreviews = ReorderBotMediaPreviews(
    botUserId = this.botUserId,
    languageCode = this.languageCode,
    fileIds = this.fileIds
)

fun TdApi.ReorderChatFolders.toDomain(): ReorderChatFolders = ReorderChatFolders(
    chatFolderIds = this.chatFolderIds,
    mainChatListPosition = this.mainChatListPosition
)

fun TdApi.ReorderGiftCollectionGifts.toDomain(): ReorderGiftCollectionGifts = ReorderGiftCollectionGifts(
    ownerId = this.ownerId.toDomain(),
    collectionId = this.collectionId,
    receivedGiftIds = this.receivedGiftIds.toList()
)

fun TdApi.ReorderGiftCollections.toDomain(): ReorderGiftCollections = ReorderGiftCollections(
    ownerId = this.ownerId.toDomain(),
    collectionIds = this.collectionIds
)

fun TdApi.ReorderInstalledStickerSets.toDomain(): ReorderInstalledStickerSets = ReorderInstalledStickerSets(
    stickerType = this.stickerType.toDomain(),
    stickerSetIds = this.stickerSetIds
)

fun TdApi.ReorderQuickReplyShortcuts.toDomain(): ReorderQuickReplyShortcuts = ReorderQuickReplyShortcuts(
    shortcutIds = this.shortcutIds
)

fun TdApi.ReorderStoryAlbumStories.toDomain(): ReorderStoryAlbumStories = ReorderStoryAlbumStories(
    chatId = this.chatId,
    storyAlbumId = this.storyAlbumId,
    storyIds = this.storyIds
)

fun TdApi.ReorderStoryAlbums.toDomain(): ReorderStoryAlbums = ReorderStoryAlbums(
    chatId = this.chatId,
    storyAlbumIds = this.storyAlbumIds
)

fun TdApi.ReorderSupergroupActiveUsernames.toDomain(): ReorderSupergroupActiveUsernames = ReorderSupergroupActiveUsernames(
    supergroupId = this.supergroupId,
    usernames = this.usernames.toList()
)

