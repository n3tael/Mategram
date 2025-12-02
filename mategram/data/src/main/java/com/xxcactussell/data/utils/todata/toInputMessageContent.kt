package com.xxcactussell.data.utils.todata

import com.xxcactussell.domain.messages.model.InputFile
import com.xxcactussell.domain.messages.model.InputMessageContent
import com.xxcactussell.domain.messages.model.InputThumbnail
import org.drinkless.tdlib.TdApi

fun InputMessageContent.toData() : TdApi.InputMessageContent? {
    return when(this) {
        is InputMessageContent.Text -> TdApi.InputMessageText(
            this.text.toData(),
            this.linkPreviewOptions?.toData(),
            this.clearDraft
        )
        is InputMessageContent.Photo -> TdApi.InputMessagePhoto(
            this.photo.toData(),
            this.thumbnail.toData(),
            this.addedStickerFileIds,
            this.width,
            this.height,
            this.caption.toData(),
            this.showCaptionAboveMedia,
            null,
            this.hasSpoiler
        )
        is InputMessageContent.Video -> TdApi.InputMessageVideo(
            this.video.toData(),
            this.thumbnail.toData(),
            this.cover.toData(),
            this.startTimestamp,
            this.addedStickerFileIds,
            this.duration,
            this.width,
            this.height,
            this.supportStreaming,
            this.caption.toData(),
            this.showCaptionAboveMedia,
            null,
            this.hasSpoiler
        )
        is InputMessageContent.Animation -> TdApi.InputMessageAnimation(
            this.animation.toData(),
            this.thumbnail.toData(),
            this.addedStickerFileIds,
            this.duration,
            this.width,
            this.height,
            this.caption.toData(),
            this.showCaptionAboveMedia,
            this.hasSpoiler
        )
        is InputMessageContent.Audio -> TdApi.InputMessageAudio(
            this.audio.toData(),
            this.albumCoverThumbnail.toData(),
            this.duration,
            this.title,
            this.performer,
            this.caption.toData(),
        )
        is InputMessageContent.Document -> TdApi.InputMessageDocument(
            this.document.toData(),
            this.thumbnail.toData(),
            this.disableContentTypeDetection,
            this.caption.toData(),
        )
        is InputMessageContent.Sticker -> TdApi.InputMessageSticker(
            this.sticker.toData(),
            this.thumbnail.toData(),
            this.width,
            this.height,
            this.emoji
        )
        else -> null
    }
}

fun InputFile.toData() : TdApi.InputFile? {
    return when(this) {
        is InputFile.FileId -> TdApi.InputFileId(this.id)
        is InputFile.Remote -> TdApi.InputFileRemote(this.id)
        is InputFile.Local -> TdApi.InputFileLocal(this.path)
        is InputFile.Generated -> TdApi.InputFileGenerated(this.originalPath, this.conversion, this.expectedSize)
        else -> null
    }
}

fun InputThumbnail.toData() : TdApi.InputThumbnail? {
    return TdApi.InputThumbnail(this.file.toData(), this.width, this.height)
}