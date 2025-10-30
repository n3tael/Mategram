package com.xxcactussell.data.utils.todomain

import com.xxcactussell.data.utils.toDomain
import com.xxcactussell.domain.messages.model.Audio
import org.drinkless.tdlib.TdApi

fun TdApi.Audio.toDomain() : Audio {
    return Audio(
        duration = this.duration,
        title = this.title,
        performer = this.performer,
        fileName = this.fileName,
        mimeType = this.mimeType,
        audio = this.audio.toDomain(),
        albumCoverMiniThumbnail = this.albumCoverMinithumbnail?.toDomain(),
        albumCoverThumbnail = this.albumCoverThumbnail?.toDomain(),
        externalAlbumCovers = this.externalAlbumCovers.map {
            it.toDomain()
        }
    )
}