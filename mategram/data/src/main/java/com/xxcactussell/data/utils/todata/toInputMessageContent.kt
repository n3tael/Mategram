package com.xxcactussell.data.utils.todata

import com.xxcactussell.domain.files.model.File
import com.xxcactussell.domain.messages.model.FormattedText
import com.xxcactussell.domain.messages.model.InputFile
import com.xxcactussell.domain.messages.model.InputMessageContent
import com.xxcactussell.domain.messages.model.InputThumbnail
import com.xxcactussell.domain.messages.model.LinkOption
import com.xxcactussell.domain.messages.model.TextEntity
import com.xxcactussell.domain.messages.model.TextEntityType
import org.drinkless.tdlib.TdApi
import kotlin.concurrent.thread

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

fun FormattedText.toData() : TdApi.FormattedText {
    return TdApi.FormattedText(
        this.text,
        this.entities.map {
            it.toData()
        }.toTypedArray()
    )
}

fun TextEntity.toData() : TdApi.TextEntity {
    return TdApi.TextEntity(
        this.offset,
        this.length,
        this.type.toData(),
    )
}

fun TextEntityType.toData(): TdApi.TextEntityType {
    return when (this) {
        TextEntityType.Mention -> TdApi.TextEntityTypeMention()
        TextEntityType.Hashtag -> TdApi.TextEntityTypeHashtag()
        TextEntityType.Cashtag -> TdApi.TextEntityTypeCashtag()
        TextEntityType.BotCommand -> TdApi.TextEntityTypeBotCommand()
        TextEntityType.Url -> TdApi.TextEntityTypeUrl()
        TextEntityType.Email -> TdApi.TextEntityTypeEmailAddress()
        TextEntityType.Phone -> TdApi.TextEntityTypePhoneNumber()
        TextEntityType.Card -> TdApi.TextEntityTypeBankCardNumber()
        TextEntityType.Bold -> TdApi.TextEntityTypeBold()
        TextEntityType.Italic -> TdApi.TextEntityTypeItalic()
        TextEntityType.Underline -> TdApi.TextEntityTypeUnderline()
        TextEntityType.Strikethrough -> TdApi.TextEntityTypeStrikethrough()
        TextEntityType.Spoiler -> TdApi.TextEntityTypeSpoiler()
        TextEntityType.Code -> TdApi.TextEntityTypeCode()
        TextEntityType.Pre -> TdApi.TextEntityTypePre()
        TextEntityType.BlockQuote -> TdApi.TextEntityTypeBlockQuote()
        TextEntityType.ExpandableBlockQuote -> TdApi.TextEntityTypeExpandableBlockQuote()
        is TextEntityType.PreCode -> TdApi.TextEntityTypePreCode(this.language)
        is TextEntityType.TextUrl -> TdApi.TextEntityTypeTextUrl(this.url)
        is TextEntityType.MentionName -> TdApi.TextEntityTypeMentionName(this.id)
        is TextEntityType.CustomEmoji -> TdApi.TextEntityTypeCustomEmoji(this.id)
        is TextEntityType.Timestamp -> TdApi.TextEntityTypeMediaTimestamp(this.timestamp) // TDLib использует MediaTimestamp
    }
}

fun LinkOption.toData() : TdApi.LinkPreviewOptions {
    return TdApi.LinkPreviewOptions(
        this.isDisabled == true,
        this.url,
        this.forceSmallMedia == true,
        this.forceLargeMedia == true,
        this.showAboveText == true
    )
}