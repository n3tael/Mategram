package com.xxcactussell.data.utils.mappers.text

import com.xxcactussell.data.utils.mappers.formatted.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.Text.toDomain(): Text = Text(
    text = this.text
)

fun TdApi.TextEntities.toDomain(): TextEntities = TextEntities(
    entities = this.entities.map { it.toDomain() }
)

fun TdApi.TextEntity.toDomain(): TextEntity = TextEntity(
    offset = this.offset,
    length = this.length,
    type = this.type.toDomain()
)

fun TdApi.TextEntityType.toDomain(): TextEntityType = when(this) {
    is TdApi.TextEntityTypeMention -> this.toDomain()
    is TdApi.TextEntityTypeHashtag -> this.toDomain()
    is TdApi.TextEntityTypeCashtag -> this.toDomain()
    is TdApi.TextEntityTypeBotCommand -> this.toDomain()
    is TdApi.TextEntityTypeUrl -> this.toDomain()
    is TdApi.TextEntityTypeEmailAddress -> this.toDomain()
    is TdApi.TextEntityTypePhoneNumber -> this.toDomain()
    is TdApi.TextEntityTypeBankCardNumber -> this.toDomain()
    is TdApi.TextEntityTypeBold -> this.toDomain()
    is TdApi.TextEntityTypeItalic -> this.toDomain()
    is TdApi.TextEntityTypeUnderline -> this.toDomain()
    is TdApi.TextEntityTypeStrikethrough -> this.toDomain()
    is TdApi.TextEntityTypeSpoiler -> this.toDomain()
    is TdApi.TextEntityTypeCode -> this.toDomain()
    is TdApi.TextEntityTypePre -> this.toDomain()
    is TdApi.TextEntityTypePreCode -> this.toDomain()
    is TdApi.TextEntityTypeBlockQuote -> this.toDomain()
    is TdApi.TextEntityTypeExpandableBlockQuote -> this.toDomain()
    is TdApi.TextEntityTypeTextUrl -> this.toDomain()
    is TdApi.TextEntityTypeMentionName -> this.toDomain()
    is TdApi.TextEntityTypeCustomEmoji -> this.toDomain()
    is TdApi.TextEntityTypeMediaTimestamp -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.TextEntityTypeBankCardNumber.toDomain(): TextEntityTypeBankCardNumber = TextEntityTypeBankCardNumber

fun TdApi.TextEntityTypeBlockQuote.toDomain(): TextEntityTypeBlockQuote = TextEntityTypeBlockQuote

fun TdApi.TextEntityTypeBold.toDomain(): TextEntityTypeBold = TextEntityTypeBold

fun TdApi.TextEntityTypeBotCommand.toDomain(): TextEntityTypeBotCommand = TextEntityTypeBotCommand

fun TdApi.TextEntityTypeCashtag.toDomain(): TextEntityTypeCashtag = TextEntityTypeCashtag

fun TdApi.TextEntityTypeCode.toDomain(): TextEntityTypeCode = TextEntityTypeCode

fun TdApi.TextEntityTypeCustomEmoji.toDomain(): TextEntityTypeCustomEmoji = TextEntityTypeCustomEmoji(
    customEmojiId = this.customEmojiId
)

fun TdApi.TextEntityTypeEmailAddress.toDomain(): TextEntityTypeEmailAddress = TextEntityTypeEmailAddress

fun TdApi.TextEntityTypeExpandableBlockQuote.toDomain(): TextEntityTypeExpandableBlockQuote = TextEntityTypeExpandableBlockQuote

fun TdApi.TextEntityTypeHashtag.toDomain(): TextEntityTypeHashtag = TextEntityTypeHashtag

fun TdApi.TextEntityTypeItalic.toDomain(): TextEntityTypeItalic = TextEntityTypeItalic

fun TdApi.TextEntityTypeMediaTimestamp.toDomain(): TextEntityTypeMediaTimestamp = TextEntityTypeMediaTimestamp(
    mediaTimestamp = this.mediaTimestamp
)

fun TdApi.TextEntityTypeMention.toDomain(): TextEntityTypeMention = TextEntityTypeMention

fun TdApi.TextEntityTypeMentionName.toDomain(): TextEntityTypeMentionName = TextEntityTypeMentionName(
    userId = this.userId
)

fun TdApi.TextEntityTypePhoneNumber.toDomain(): TextEntityTypePhoneNumber = TextEntityTypePhoneNumber

fun TdApi.TextEntityTypePre.toDomain(): TextEntityTypePre = TextEntityTypePre

fun TdApi.TextEntityTypePreCode.toDomain(): TextEntityTypePreCode = TextEntityTypePreCode(
    language = this.language
)

fun TdApi.TextEntityTypeSpoiler.toDomain(): TextEntityTypeSpoiler = TextEntityTypeSpoiler

fun TdApi.TextEntityTypeStrikethrough.toDomain(): TextEntityTypeStrikethrough = TextEntityTypeStrikethrough

fun TdApi.TextEntityTypeTextUrl.toDomain(): TextEntityTypeTextUrl = TextEntityTypeTextUrl(
    url = this.url
)

fun TdApi.TextEntityTypeUnderline.toDomain(): TextEntityTypeUnderline = TextEntityTypeUnderline

fun TdApi.TextEntityTypeUrl.toDomain(): TextEntityTypeUrl = TextEntityTypeUrl

fun TdApi.TextParseMode.toDomain(): TextParseMode = when(this) {
    is TdApi.TextParseModeMarkdown -> this.toDomain()
    is TdApi.TextParseModeHTML -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.TextParseModeHTML.toDomain(): TextParseModeHTML = TextParseModeHTML

fun TdApi.TextParseModeMarkdown.toDomain(): TextParseModeMarkdown = TextParseModeMarkdown(
    version = this.version
)

fun TdApi.TextQuote.toDomain(): TextQuote = TextQuote(
    text = this.text.toDomain(),
    position = this.position,
    isManual = this.isManual
)

