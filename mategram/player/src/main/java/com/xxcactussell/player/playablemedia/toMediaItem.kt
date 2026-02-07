package com.xxcactussell.player.playablemedia

import android.os.Bundle
import androidx.core.net.toUri
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata

fun PlayableMedia.toMediaItem(): MediaItem {
    val extras = Bundle().apply {
        putString("MEDIA_ID", id)
        putLong("CHAT_ID", chatId)
        putBoolean("IS_VIDEO", this@toMediaItem is PlayableMedia.Video)

        if (this@toMediaItem is PlayableMedia.Voice) {
            putByteArray("WAVEFORM", waveform)
        }
    }

    val metadata = MediaMetadata.Builder()
        .setArtist(artist)
        .setArtworkUri(artworkUri?.toUri())
        .setExtras(extras)
        .build()

    return MediaItem.Builder()
        .setUri(url.toUri())
        .setMediaId(id)
        .setMediaMetadata(metadata)
        .build()
}