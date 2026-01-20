package com.xxcactussell.data.utils.mappers.answer

import com.xxcactussell.data.utils.mappers.inline.toDomain
import com.xxcactussell.data.utils.mappers.input.toDomain
import com.xxcactussell.data.utils.mappers.shipping.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.AnswerCallbackQuery.toDomain(): AnswerCallbackQuery = AnswerCallbackQuery(
    callbackQueryId = this.callbackQueryId,
    text = this.text,
    showAlert = this.showAlert,
    url = this.url,
    cacheTime = this.cacheTime
)

fun TdApi.AnswerCustomQuery.toDomain(): AnswerCustomQuery = AnswerCustomQuery(
    customQueryId = this.customQueryId,
    data = this.data
)

fun TdApi.AnswerInlineQuery.toDomain(): AnswerInlineQuery = AnswerInlineQuery(
    inlineQueryId = this.inlineQueryId,
    isPersonal = this.isPersonal,
    button = this.button.toDomain(),
    results = this.results.map { it.toDomain() },
    cacheTime = this.cacheTime,
    nextOffset = this.nextOffset
)

fun TdApi.AnswerPreCheckoutQuery.toDomain(): AnswerPreCheckoutQuery = AnswerPreCheckoutQuery(
    preCheckoutQueryId = this.preCheckoutQueryId,
    errorMessage = this.errorMessage
)

fun TdApi.AnswerShippingQuery.toDomain(): AnswerShippingQuery = AnswerShippingQuery(
    shippingQueryId = this.shippingQueryId,
    shippingOptions = this.shippingOptions.map { it.toDomain() },
    errorMessage = this.errorMessage
)

fun TdApi.AnswerWebAppQuery.toDomain(): AnswerWebAppQuery = AnswerWebAppQuery(
    webAppQueryId = this.webAppQueryId,
    result = this.result.toDomain()
)

