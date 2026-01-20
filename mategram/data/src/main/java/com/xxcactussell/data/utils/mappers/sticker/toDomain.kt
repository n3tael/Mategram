package com.xxcactussell.data.utils.mappers.sticker

import com.xxcactussell.data.utils.mappers.emojis.toDomain
import com.xxcactussell.data.utils.mappers.file.toDomain
import com.xxcactussell.data.utils.mappers.mask.toDomain
import com.xxcactussell.data.utils.mappers.outline.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.Sticker.toDomain(): Sticker = Sticker(
    id = this.id,
    setId = this.setId,
    width = this.width,
    height = this.height,
    emoji = this.emoji,
    format = this.format.toDomain(),
    fullType = this.fullType.toDomain(),
    thumbnail = this.thumbnail?.toDomain(),
    sticker = this.sticker.toDomain()
)

fun TdApi.StickerFormat.toDomain(): StickerFormat = when(this) {
    is TdApi.StickerFormatWebp -> this.toDomain()
    is TdApi.StickerFormatTgs -> this.toDomain()
    is TdApi.StickerFormatWebm -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.StickerFormatTgs.toDomain(): StickerFormatTgs = StickerFormatTgs

fun TdApi.StickerFormatWebm.toDomain(): StickerFormatWebm = StickerFormatWebm

fun TdApi.StickerFormatWebp.toDomain(): StickerFormatWebp = StickerFormatWebp

fun TdApi.StickerFullType.toDomain(): StickerFullType = when(this) {
    is TdApi.StickerFullTypeRegular -> this.toDomain()
    is TdApi.StickerFullTypeMask -> this.toDomain()
    is TdApi.StickerFullTypeCustomEmoji -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.StickerFullTypeCustomEmoji.toDomain(): StickerFullTypeCustomEmoji = StickerFullTypeCustomEmoji(
    customEmojiId = this.customEmojiId,
    needsRepainting = this.needsRepainting
)

fun TdApi.StickerFullTypeMask.toDomain(): StickerFullTypeMask = StickerFullTypeMask(
    maskPosition = this.maskPosition?.toDomain()
)

fun TdApi.StickerFullTypeRegular.toDomain(): StickerFullTypeRegular = StickerFullTypeRegular(
    premiumAnimation = this.premiumAnimation?.toDomain()
)

fun TdApi.StickerSet.toDomain(): StickerSet = StickerSet(
    id = this.id,
    title = this.title,
    name = this.name,
    thumbnail = this.thumbnail?.toDomain(),
    thumbnailOutline = this.thumbnailOutline?.toDomain(),
    isOwned = this.isOwned,
    isInstalled = this.isInstalled,
    isArchived = this.isArchived,
    isOfficial = this.isOfficial,
    stickerType = this.stickerType.toDomain(),
    needsRepainting = this.needsRepainting,
    isAllowedAsChatEmojiStatus = this.isAllowedAsChatEmojiStatus,
    isViewed = this.isViewed,
    stickers = this.stickers.map { it.toDomain() },
    emojis = this.emojis.map { it.toDomain() }
)

fun TdApi.StickerSetInfo.toDomain(): StickerSetInfo = StickerSetInfo(
    id = this.id,
    title = this.title,
    name = this.name,
    thumbnail = this.thumbnail?.toDomain(),
    thumbnailOutline = this.thumbnailOutline?.toDomain(),
    isOwned = this.isOwned,
    isInstalled = this.isInstalled,
    isArchived = this.isArchived,
    isOfficial = this.isOfficial,
    stickerType = this.stickerType.toDomain(),
    needsRepainting = this.needsRepainting,
    isAllowedAsChatEmojiStatus = this.isAllowedAsChatEmojiStatus,
    isViewed = this.isViewed,
    size = this.size,
    covers = this.covers.map { it.toDomain() }
)

fun TdApi.StickerSets.toDomain(): StickerSets = StickerSets(
    totalCount = this.totalCount,
    sets = this.sets.map { it.toDomain() }
)

fun TdApi.StickerType.toDomain(): StickerType = when(this) {
    is TdApi.StickerTypeRegular -> this.toDomain()
    is TdApi.StickerTypeMask -> this.toDomain()
    is TdApi.StickerTypeCustomEmoji -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.StickerTypeCustomEmoji.toDomain(): StickerTypeCustomEmoji = StickerTypeCustomEmoji

fun TdApi.StickerTypeMask.toDomain(): StickerTypeMask = StickerTypeMask

fun TdApi.StickerTypeRegular.toDomain(): StickerTypeRegular = StickerTypeRegular

