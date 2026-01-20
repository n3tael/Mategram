package com.xxcactussell.data.utils.mappers.translate

import com.xxcactussell.data.utils.mappers.formatted.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun TranslateMessageText.toData(): TdApi.TranslateMessageText = TdApi.TranslateMessageText(
    this.chatId,
    this.messageId,
    this.toLanguageCode
)

fun TranslateText.toData(): TdApi.TranslateText = TdApi.TranslateText(
    this.text.toData(),
    this.toLanguageCode
)

