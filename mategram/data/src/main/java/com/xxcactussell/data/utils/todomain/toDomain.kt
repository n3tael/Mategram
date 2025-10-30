package com.xxcactussell.data.utils

import com.xxcactussell.domain.messages.model.FormattedText
import com.xxcactussell.domain.messages.model.TextEntity
import com.xxcactussell.domain.messages.model.TextEntityType
import org.drinkless.tdlib.TdApi

fun TdApi.FormattedText.toDomain() : FormattedText {
    val text = this.text
    val entities = this.entities.map { entity ->
        val length = entity.length
        val offset = entity.offset
        val type = when (entity.type) {
            is TdApi.TextEntityTypeMention -> TextEntityType.Mention
            is TdApi.TextEntityTypeHashtag -> TextEntityType.Hashtag
            is TdApi.TextEntityTypeCashtag -> TextEntityType.Cashtag
            is TdApi.TextEntityTypeBotCommand -> TextEntityType.BotCommand
            is TdApi.TextEntityTypeUrl -> TextEntityType.Url
            is TdApi.TextEntityTypeEmailAddress -> TextEntityType.Email
            is TdApi.TextEntityTypePhoneNumber -> TextEntityType.Phone
            is TdApi.TextEntityTypeBankCardNumber-> TextEntityType.Card
            is TdApi.TextEntityTypeBold -> TextEntityType.Bold
            is TdApi.TextEntityTypeItalic -> TextEntityType.Italic
            is TdApi.TextEntityTypeUnderline -> TextEntityType.Underline
            is TdApi.TextEntityTypeStrikethrough -> TextEntityType.Strikethrough
            is TdApi.TextEntityTypeSpoiler -> TextEntityType.Spoiler
            is TdApi.TextEntityTypeCode -> TextEntityType.Code
            is TdApi.TextEntityTypePre -> TextEntityType.Pre
            is TdApi.TextEntityTypePreCode -> TextEntityType.PreCode((entity.type as TdApi.TextEntityTypePreCode).language)
            is TdApi.TextEntityTypeBlockQuote -> TextEntityType.BlockQuote
            is TdApi.TextEntityTypeExpandableBlockQuote-> TextEntityType.ExpandableBlockQuote
            is TdApi.TextEntityTypeTextUrl -> TextEntityType.TextUrl((entity.type as TdApi.TextEntityTypeTextUrl).url)
            is TdApi.TextEntityTypeMentionName -> TextEntityType.MentionName((entity.type as TdApi.TextEntityTypeMentionName).userId)
            is TdApi.TextEntityTypeCustomEmoji -> TextEntityType.CustomEmoji((entity.type as TdApi.TextEntityTypeCustomEmoji).customEmojiId)
            is TdApi.TextEntityTypeMediaTimestamp -> TextEntityType.Timestamp((entity.type as TdApi.TextEntityTypeMediaTimestamp).mediaTimestamp)
            else -> TextEntityType.Bold
        }
        TextEntity(
            offset,
            length,
            type
        )
    }
    return FormattedText(
        text,
        entities
    )
}