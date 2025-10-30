package com.xxcactussell.domain.messages.model

import com.xxcactussell.domain.files.model.File

sealed interface ThumbnailFormat {
    object Jpeg : ThumbnailFormat
    object Gif : ThumbnailFormat
    object Mpeg4 : ThumbnailFormat
    object Png : ThumbnailFormat
    object Tgs : ThumbnailFormat
    object Webm : ThumbnailFormat
    object Webp : ThumbnailFormat
    object Unknown : ThumbnailFormat
}

data class Thumbnail(
    val height: Int,
    val width: Int,
    val file: File,
    val format: ThumbnailFormat
)

sealed interface Video {
    data class Main(
        val duration: Int,
        val height: Int,
        val width: Int,
        val fileName: String,
        val mimeType: String,
        val hasSticker: Boolean,
        val supportsStreaming: Boolean,
        val thumbnail: Thumbnail?,
        val video: File
    ) : Video
    data class Alternative(
        val id: Long,
        val height: Int,
        val width: Int,
        val codec: String,
        val hlsFile: File,
        val file: File
    ) : Video
}