package com.xxcactussell.data.utils.mappers.translate

import com.xxcactussell.data.utils.mappers.formatted.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.TranslateMessageText.toDomain(): TranslateMessageText = TranslateMessageText(
    chatId = this.chatId,
    messageId = this.messageId,
    toLanguageCode = this.toLanguageCode
)

fun TdApi.TranslateText.toDomain(): TranslateText = TranslateText(
    text = this.text.toDomain(),
    toLanguageCode = this.toLanguageCode
)

