package com.xxcactussell.data.utils.mappers.text

import com.xxcactussell.data.utils.mappers.formatted.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun Text.toData(): TdApi.Text = TdApi.Text(
    this.text
)

fun TextEntities.toData(): TdApi.TextEntities = TdApi.TextEntities(
    this.entities.map { it.toData() }.toTypedArray()
)

fun TextEntity.toData(): TdApi.TextEntity = TdApi.TextEntity(
    this.offset,
    this.length,
    this.type.toData()
)

fun TextEntityType.toData(): TdApi.TextEntityType = when(this) {
    is TextEntityTypeMention -> this.toData()
    is TextEntityTypeHashtag -> this.toData()
    is TextEntityTypeCashtag -> this.toData()
    is TextEntityTypeBotCommand -> this.toData()
    is TextEntityTypeUrl -> this.toData()
    is TextEntityTypeEmailAddress -> this.toData()
    is TextEntityTypePhoneNumber -> this.toData()
    is TextEntityTypeBankCardNumber -> this.toData()
    is TextEntityTypeBold -> this.toData()
    is TextEntityTypeItalic -> this.toData()
    is TextEntityTypeUnderline -> this.toData()
    is TextEntityTypeStrikethrough -> this.toData()
    is TextEntityTypeSpoiler -> this.toData()
    is TextEntityTypeCode -> this.toData()
    is TextEntityTypePre -> this.toData()
    is TextEntityTypePreCode -> this.toData()
    is TextEntityTypeBlockQuote -> this.toData()
    is TextEntityTypeExpandableBlockQuote -> this.toData()
    is TextEntityTypeTextUrl -> this.toData()
    is TextEntityTypeMentionName -> this.toData()
    is TextEntityTypeCustomEmoji -> this.toData()
    is TextEntityTypeMediaTimestamp -> this.toData()
}

fun TextEntityTypeBankCardNumber.toData(): TdApi.TextEntityTypeBankCardNumber = TdApi.TextEntityTypeBankCardNumber(
)

fun TextEntityTypeBlockQuote.toData(): TdApi.TextEntityTypeBlockQuote = TdApi.TextEntityTypeBlockQuote(
)

fun TextEntityTypeBold.toData(): TdApi.TextEntityTypeBold = TdApi.TextEntityTypeBold(
)

fun TextEntityTypeBotCommand.toData(): TdApi.TextEntityTypeBotCommand = TdApi.TextEntityTypeBotCommand(
)

fun TextEntityTypeCashtag.toData(): TdApi.TextEntityTypeCashtag = TdApi.TextEntityTypeCashtag(
)

fun TextEntityTypeCode.toData(): TdApi.TextEntityTypeCode = TdApi.TextEntityTypeCode(
)

fun TextEntityTypeCustomEmoji.toData(): TdApi.TextEntityTypeCustomEmoji = TdApi.TextEntityTypeCustomEmoji(
    this.customEmojiId
)

fun TextEntityTypeEmailAddress.toData(): TdApi.TextEntityTypeEmailAddress = TdApi.TextEntityTypeEmailAddress(
)

fun TextEntityTypeExpandableBlockQuote.toData(): TdApi.TextEntityTypeExpandableBlockQuote = TdApi.TextEntityTypeExpandableBlockQuote(
)

fun TextEntityTypeHashtag.toData(): TdApi.TextEntityTypeHashtag = TdApi.TextEntityTypeHashtag(
)

fun TextEntityTypeItalic.toData(): TdApi.TextEntityTypeItalic = TdApi.TextEntityTypeItalic(
)

fun TextEntityTypeMediaTimestamp.toData(): TdApi.TextEntityTypeMediaTimestamp = TdApi.TextEntityTypeMediaTimestamp(
    this.mediaTimestamp
)

fun TextEntityTypeMention.toData(): TdApi.TextEntityTypeMention = TdApi.TextEntityTypeMention(
)

fun TextEntityTypeMentionName.toData(): TdApi.TextEntityTypeMentionName = TdApi.TextEntityTypeMentionName(
    this.userId
)

fun TextEntityTypePhoneNumber.toData(): TdApi.TextEntityTypePhoneNumber = TdApi.TextEntityTypePhoneNumber(
)

fun TextEntityTypePre.toData(): TdApi.TextEntityTypePre = TdApi.TextEntityTypePre(
)

fun TextEntityTypePreCode.toData(): TdApi.TextEntityTypePreCode = TdApi.TextEntityTypePreCode(
    this.language
)

fun TextEntityTypeSpoiler.toData(): TdApi.TextEntityTypeSpoiler = TdApi.TextEntityTypeSpoiler(
)

fun TextEntityTypeStrikethrough.toData(): TdApi.TextEntityTypeStrikethrough = TdApi.TextEntityTypeStrikethrough(
)

fun TextEntityTypeTextUrl.toData(): TdApi.TextEntityTypeTextUrl = TdApi.TextEntityTypeTextUrl(
    this.url
)

fun TextEntityTypeUnderline.toData(): TdApi.TextEntityTypeUnderline = TdApi.TextEntityTypeUnderline(
)

fun TextEntityTypeUrl.toData(): TdApi.TextEntityTypeUrl = TdApi.TextEntityTypeUrl(
)

fun TextParseMode.toData(): TdApi.TextParseMode = when(this) {
    is TextParseModeMarkdown -> this.toData()
    is TextParseModeHTML -> this.toData()
}

fun TextParseModeHTML.toData(): TdApi.TextParseModeHTML = TdApi.TextParseModeHTML(
)

fun TextParseModeMarkdown.toData(): TdApi.TextParseModeMarkdown = TdApi.TextParseModeMarkdown(
    this.version
)

fun TextQuote.toData(): TdApi.TextQuote = TdApi.TextQuote(
    this.text.toData(),
    this.position,
    this.isManual
)

