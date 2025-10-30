package com.xxcactussell.domain.messages.model

import com.xxcactussell.domain.files.model.File

data class MiniThumbnail(
    val height: Int,
    val width: Int,
    val data: ByteArray?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MiniThumbnail

        if (height != other.height) return false
        if (width != other.width) return false
        if (!data.contentEquals(other.data)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = height
        result = 31 * result + width
        result = 31 * result + (data?.contentHashCode() ?: 0)
        return result
    }
}

data class PhotoSize(
    val type: String,
    val photo: File,
    val width: Int,
    val height: Int,
)

data class Photo(
    val hasStickers: Boolean,
    val miniThumbnail: MiniThumbnail?,
    val sizes: List<PhotoSize>
)