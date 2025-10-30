package com.xxcactussell.domain.messages.model

import com.xxcactussell.domain.files.model.File

sealed interface StickerFormat {
    object Webp : StickerFormat
    object Tgs : StickerFormat
    object Webm : StickerFormat
    object Unknown: StickerFormat
}

sealed interface MaskPoint {
    object Forehead : MaskPoint
    object Eyes : MaskPoint
    object Mouth : MaskPoint
    object Chin : MaskPoint
    object Unknown : MaskPoint
}

data class MaskPosition(
    val point: MaskPoint,
    val xShift: Double,
    val yShift: Double,
    val scale: Double
)

sealed interface StickerType {
    data class Regular(val premiumAnimation: File? = null): StickerType
    data class Mask(val maskPosition: MaskPosition? = null): StickerType
    data class CustomEmoji(val id: Long, val needsRepainting: Boolean): StickerType
    object Unknown: StickerType

}

data class Sticker(
    val id: Long,
    val setIt: Long,
    val width: Int,
    val height: Int,
    val emoji: String,
    val format: StickerFormat,
    val type: StickerType,
    val thumbnail: Thumbnail?,
    val sticker: File
)