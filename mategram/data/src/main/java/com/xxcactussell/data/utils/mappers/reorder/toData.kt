package com.xxcactussell.data.utils.mappers.reorder

import com.xxcactussell.data.utils.mappers.message.toData
import com.xxcactussell.data.utils.mappers.sticker.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun ReorderActiveUsernames.toData(): TdApi.ReorderActiveUsernames = TdApi.ReorderActiveUsernames(
    this.usernames.toTypedArray()
)

fun ReorderBotActiveUsernames.toData(): TdApi.ReorderBotActiveUsernames = TdApi.ReorderBotActiveUsernames(
    this.botUserId,
    this.usernames.toTypedArray()
)

fun ReorderBotMediaPreviews.toData(): TdApi.ReorderBotMediaPreviews = TdApi.ReorderBotMediaPreviews(
    this.botUserId,
    this.languageCode,
    this.fileIds
)

fun ReorderChatFolders.toData(): TdApi.ReorderChatFolders = TdApi.ReorderChatFolders(
    this.chatFolderIds,
    this.mainChatListPosition
)

fun ReorderGiftCollectionGifts.toData(): TdApi.ReorderGiftCollectionGifts = TdApi.ReorderGiftCollectionGifts(
    this.ownerId.toData(),
    this.collectionId,
    this.receivedGiftIds.toTypedArray()
)

fun ReorderGiftCollections.toData(): TdApi.ReorderGiftCollections = TdApi.ReorderGiftCollections(
    this.ownerId.toData(),
    this.collectionIds
)

fun ReorderInstalledStickerSets.toData(): TdApi.ReorderInstalledStickerSets = TdApi.ReorderInstalledStickerSets(
    this.stickerType.toData(),
    this.stickerSetIds
)

fun ReorderQuickReplyShortcuts.toData(): TdApi.ReorderQuickReplyShortcuts = TdApi.ReorderQuickReplyShortcuts(
    this.shortcutIds
)

fun ReorderStoryAlbumStories.toData(): TdApi.ReorderStoryAlbumStories = TdApi.ReorderStoryAlbumStories(
    this.chatId,
    this.storyAlbumId,
    this.storyIds
)

fun ReorderStoryAlbums.toData(): TdApi.ReorderStoryAlbums = TdApi.ReorderStoryAlbums(
    this.chatId,
    this.storyAlbumIds
)

fun ReorderSupergroupActiveUsernames.toData(): TdApi.ReorderSupergroupActiveUsernames = TdApi.ReorderSupergroupActiveUsernames(
    this.supergroupId,
    this.usernames.toTypedArray()
)

