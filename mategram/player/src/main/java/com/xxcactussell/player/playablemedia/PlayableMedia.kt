package com.xxcactussell.player.playablemedia

import com.xxcactussell.domain.ChatPhotoInfo

sealed interface PlayableMedia {
    val chatId: Long
    val id: String
    val fileId: Int
    val url: String
    val artist: String
    val duration: Long
    val artworkUri: String?
    val avatar: ChatPhotoInfo?

    data class Voice(
        override val chatId: Long,
        override val id: String,
        override val url: String,
        override val artist: String,
        override val artworkUri: String?,
        override val avatar: ChatPhotoInfo? = null,
        val waveform: ByteArray?,
        override val fileId: Int
    ) : PlayableMedia {
        override val duration: Long = 0
    }

    data class Video(
        override val chatId: Long,
        override val id: String,
        override val url: String,
        override val artist: String,
        override val artworkUri: String?,
        override val avatar: ChatPhotoInfo? = null,
        val width: Int,
        val height: Int,
        override val fileId: Int
    ) : PlayableMedia {
        override val duration: Long = 0
    }

    companion object
}