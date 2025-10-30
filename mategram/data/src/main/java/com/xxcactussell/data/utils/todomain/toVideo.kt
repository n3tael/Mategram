package com.xxcactussell.data.utils

import com.xxcactussell.domain.messages.model.Thumbnail
import com.xxcactussell.domain.messages.model.ThumbnailFormat
import com.xxcactussell.domain.messages.model.Video
import org.drinkless.tdlib.TdApi

fun TdApi.Video.toDomain(): Video.Main {
    return Video.Main(duration, height, width, fileName, mimeType, hasStickers, supportsStreaming, thumbnail?.toDomain(), video.toDomain())
}
fun TdApi.AlternativeVideo.toAlternativeDomain(): Video.Alternative {
    val hlsFile = this.hlsFile.toDomain()
    val file = this.video.toDomain()
    return Video.Alternative(id, height, width, codec, hlsFile, file)
}

fun TdApi.Thumbnail.toDomain() : Thumbnail {
    return Thumbnail(
        height,
        width,
        this.file.toDomain(),
        this.format.toDomain()
    )
}

fun TdApi.ThumbnailFormat.toDomain() : ThumbnailFormat {
    return when(this.constructor) {
        TdApi.ThumbnailFormatJpeg.CONSTRUCTOR -> ThumbnailFormat.Jpeg
        TdApi.ThumbnailFormatGif.CONSTRUCTOR -> ThumbnailFormat.Gif
        TdApi.ThumbnailFormatMpeg4.CONSTRUCTOR -> ThumbnailFormat.Mpeg4
        TdApi.ThumbnailFormatPng.CONSTRUCTOR -> ThumbnailFormat.Png
        TdApi.ThumbnailFormatTgs.CONSTRUCTOR -> ThumbnailFormat.Tgs
        TdApi.ThumbnailFormatWebm.CONSTRUCTOR -> ThumbnailFormat.Webm
        TdApi.ThumbnailFormatWebp.CONSTRUCTOR -> ThumbnailFormat.Webp
        else -> ThumbnailFormat.Unknown
    }
}