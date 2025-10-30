package com.xxcactussell.domain.messages.model

interface InputFile {
    data class Local(
        val path: String,
    ) : InputFile
    data class Remote(
        val id: String
    ) : InputFile
    data class FileId(
        val id: Int
    ) : InputFile
    data class Generated(
        val originalPath: String,
        val conversion: String,
        val expectedSize: Long
    ) : InputFile
}

data class InputThumbnail(
    val file: InputFile,
    val width: Int,
    val height: Int
)

interface InputMessageContent {
    data class Text(
        val text: FormattedText,
        val linkPreviewOptions: LinkOption? = null,
        val clearDraft: Boolean = true
    ) : InputMessageContent

    data class Animation(
        val animation: InputFile,
        val thumbnail: InputThumbnail,
        val addedStickerFileIds: IntArray = intArrayOf(),
        val duration: Int,
        val width: Int,
        val height: Int,
        val caption: FormattedText,
        val showCaptionAboveMedia: Boolean = false,
        val hasSpoiler: Boolean = false,
    ) : InputMessageContent {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Animation

            if (animation != other.animation) return false
            if (thumbnail != other.thumbnail) return false
            if (!addedStickerFileIds.contentEquals(other.addedStickerFileIds)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = animation.hashCode()
            result = 31 * result + thumbnail.hashCode()
            result = 31 * result + addedStickerFileIds.contentHashCode()
            return result
        }
    }

    data class Audio(
        val audio: InputFile,
        val albumCoverThumbnail: InputThumbnail,
        val duration: Int,
        val title: String,
        val performer: String,
        val caption: FormattedText
    ) : InputMessageContent

    data class Document(
        val document: InputFile,
        val thumbnail: InputThumbnail,
        val disableContentTypeDetection: Boolean,
        val caption: FormattedText
    ) : InputMessageContent

    data class Photo(
        val photo: InputFile,
        val thumbnail: InputThumbnail,
        val addedStickerFileIds: IntArray = intArrayOf(),
        val width: Int,
        val height: Int,
        val caption: FormattedText,
        val showCaptionAboveMedia: Boolean = false,
        val selfDestructType: Any? = null,
        val hasSpoiler: Boolean = false
    ) : InputMessageContent {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Photo

            if (photo != other.photo) return false
            if (thumbnail != other.thumbnail) return false
            if (!addedStickerFileIds.contentEquals(other.addedStickerFileIds)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = photo.hashCode()
            result = 31 * result + thumbnail.hashCode()
            result = 31 * result + addedStickerFileIds.contentHashCode()
            return result
        }
    }

    data class Sticker(
        val sticker: InputFile,
        val thumbnail: InputThumbnail,
        val width: Int,
        val height: Int,
        val emoji: String
    ) : InputMessageContent

    data class Video(
        val video: InputFile,
        val thumbnail: InputThumbnail,
        val cover: InputFile,
        val startTimestamp: Int,
        val addedStickerFileIds: IntArray = intArrayOf(),
        val duration: Int,
        val width: Int,
        val height: Int,
        val supportStreaming: Boolean = false,
        val caption: FormattedText,
        val showCaptionAboveMedia: Boolean = false,
        val selfDestructType: Any? = null,
        val hasSpoiler: Boolean = false
    ) : InputMessageContent {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Video

            if (startTimestamp != other.startTimestamp) return false
            if (video != other.video) return false
            if (thumbnail != other.thumbnail) return false
            if (cover != other.cover) return false
            if (!addedStickerFileIds.contentEquals(other.addedStickerFileIds)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = startTimestamp
            result = 31 * result + video.hashCode()
            result = 31 * result + thumbnail.hashCode()
            result = 31 * result + cover.hashCode()
            result = 31 * result + addedStickerFileIds.contentHashCode()
            return result
        }
    }
}