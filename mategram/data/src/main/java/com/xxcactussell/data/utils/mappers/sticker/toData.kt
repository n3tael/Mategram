package com.xxcactussell.data.utils.mappers.sticker

import com.xxcactussell.data.utils.mappers.emojis.toData
import com.xxcactussell.data.utils.mappers.file.toData
import com.xxcactussell.data.utils.mappers.mask.toData
import com.xxcactussell.data.utils.mappers.outline.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun Sticker.toData(): TdApi.Sticker = TdApi.Sticker(
    this.id,
    this.setId,
    this.width,
    this.height,
    this.emoji,
    this.format.toData(),
    this.fullType.toData(),
    this.thumbnail?.toData(),
    this.sticker.toData()
)

fun StickerFormat.toData(): TdApi.StickerFormat = when(this) {
    is StickerFormatWebp -> this.toData()
    is StickerFormatTgs -> this.toData()
    is StickerFormatWebm -> this.toData()
}

fun StickerFormatTgs.toData(): TdApi.StickerFormatTgs = TdApi.StickerFormatTgs(
)

fun StickerFormatWebm.toData(): TdApi.StickerFormatWebm = TdApi.StickerFormatWebm(
)

fun StickerFormatWebp.toData(): TdApi.StickerFormatWebp = TdApi.StickerFormatWebp(
)

fun StickerFullType.toData(): TdApi.StickerFullType = when(this) {
    is StickerFullTypeRegular -> this.toData()
    is StickerFullTypeMask -> this.toData()
    is StickerFullTypeCustomEmoji -> this.toData()
}

fun StickerFullTypeCustomEmoji.toData(): TdApi.StickerFullTypeCustomEmoji = TdApi.StickerFullTypeCustomEmoji(
    this.customEmojiId,
    this.needsRepainting
)

fun StickerFullTypeMask.toData(): TdApi.StickerFullTypeMask = TdApi.StickerFullTypeMask(
    this.maskPosition?.toData()
)

fun StickerFullTypeRegular.toData(): TdApi.StickerFullTypeRegular = TdApi.StickerFullTypeRegular(
    this.premiumAnimation?.toData()
)

fun StickerSet.toData(): TdApi.StickerSet = TdApi.StickerSet(
    this.id,
    this.title,
    this.name,
    this.thumbnail?.toData(),
    this.thumbnailOutline?.toData(),
    this.isOwned,
    this.isInstalled,
    this.isArchived,
    this.isOfficial,
    this.stickerType.toData(),
    this.needsRepainting,
    this.isAllowedAsChatEmojiStatus,
    this.isViewed,
    this.stickers.map { it.toData() }.toTypedArray(),
    this.emojis.map { it.toData() }.toTypedArray()
)

fun StickerSetInfo.toData(): TdApi.StickerSetInfo = TdApi.StickerSetInfo(
    this.id,
    this.title,
    this.name,
    this.thumbnail?.toData(),
    this.thumbnailOutline?.toData(),
    this.isOwned,
    this.isInstalled,
    this.isArchived,
    this.isOfficial,
    this.stickerType.toData(),
    this.needsRepainting,
    this.isAllowedAsChatEmojiStatus,
    this.isViewed,
    this.size,
    this.covers.map { it.toData() }.toTypedArray()
)

fun StickerSets.toData(): TdApi.StickerSets = TdApi.StickerSets(
    this.totalCount,
    this.sets.map { it.toData() }.toTypedArray()
)

fun StickerType.toData(): TdApi.StickerType = when(this) {
    is StickerTypeRegular -> this.toData()
    is StickerTypeMask -> this.toData()
    is StickerTypeCustomEmoji -> this.toData()
}

fun StickerTypeCustomEmoji.toData(): TdApi.StickerTypeCustomEmoji = TdApi.StickerTypeCustomEmoji(
)

fun StickerTypeMask.toData(): TdApi.StickerTypeMask = TdApi.StickerTypeMask(
)

fun StickerTypeRegular.toData(): TdApi.StickerTypeRegular = TdApi.StickerTypeRegular(
)

