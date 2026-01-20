package com.xxcactussell.repositories.utils

import android.net.Uri

interface FileHelper {
    suspend fun getLocalPath(uri: Uri): String?
    fun getMimeType(uri: Uri): String?
    suspend fun extractMediaMetadata(uri: Uri): MediaMetadata
}

data class MediaMetadata(
    val width: Int = 0,
    val height: Int = 0,
    val duration: Int = 0,
    val thumbnail: ByteArray? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MediaMetadata

        if (width != other.width) return false
        if (height != other.height) return false
        if (duration != other.duration) return false
        if (!thumbnail.contentEquals(other.thumbnail)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = width
        result = 31 * result + height
        result = 31 * result + duration
        result = 31 * result + (thumbnail?.contentHashCode() ?: 0)
        return result
    }
}