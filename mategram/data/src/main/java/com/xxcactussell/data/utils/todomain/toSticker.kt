package com.xxcactussell.data.utils

import com.xxcactussell.domain.messages.model.MaskPoint
import com.xxcactussell.domain.messages.model.MaskPosition
import com.xxcactussell.domain.messages.model.Sticker
import com.xxcactussell.domain.messages.model.StickerFormat
import com.xxcactussell.domain.messages.model.StickerType
import org.drinkless.tdlib.TdApi

fun TdApi.Sticker.toDomain() : Sticker {
    return Sticker(id, setId, width, height, emoji, format.toDomain(), fullType.toDomain(),
        thumbnail?.toDomain(), sticker.toDomain())
}

fun TdApi.StickerFormat.toDomain() : StickerFormat {
    return when(this.constructor) {
        TdApi.StickerFormatTgs.CONSTRUCTOR -> StickerFormat.Tgs
        TdApi.StickerFormatWebm.CONSTRUCTOR -> StickerFormat.Webm
        TdApi.StickerFormatWebp.CONSTRUCTOR -> StickerFormat.Webp
        else -> StickerFormat.Unknown
    }
}

fun TdApi.StickerFullType.toDomain() : StickerType {
    return when(this) {
        is TdApi.StickerFullTypeMask -> StickerType.Mask(
            this.maskPosition?.toDomain()
        )
        is TdApi.StickerFullTypeCustomEmoji -> StickerType.CustomEmoji(
            this.customEmojiId, this.needsRepainting
        )
        is TdApi.StickerFullTypeRegular -> StickerType.Regular(
            this.premiumAnimation?.toDomain()
        )
        else -> StickerType.Unknown
    }
}

fun TdApi.MaskPosition.toDomain() : MaskPosition {
    return MaskPosition(point.toDomain(), xShift, yShift, scale)
}
fun TdApi.MaskPoint.toDomain() : MaskPoint {
    return when(this.constructor) {
        TdApi.MaskPointForehead.CONSTRUCTOR -> MaskPoint.Forehead
        TdApi.MaskPointChin.CONSTRUCTOR -> MaskPoint.Chin
        TdApi.MaskPointEyes.CONSTRUCTOR -> MaskPoint.Eyes
        TdApi.MaskPointMouth.CONSTRUCTOR -> MaskPoint.Mouth
        else -> MaskPoint.Unknown
    }
}