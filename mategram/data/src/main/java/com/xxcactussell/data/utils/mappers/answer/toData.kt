package com.xxcactussell.data.utils.mappers.answer

import com.xxcactussell.data.utils.mappers.inline.toData
import com.xxcactussell.data.utils.mappers.input.toData
import com.xxcactussell.data.utils.mappers.shipping.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun AnswerCallbackQuery.toData(): TdApi.AnswerCallbackQuery = TdApi.AnswerCallbackQuery(
    this.callbackQueryId,
    this.text,
    this.showAlert,
    this.url,
    this.cacheTime
)

fun AnswerCustomQuery.toData(): TdApi.AnswerCustomQuery = TdApi.AnswerCustomQuery(
    this.customQueryId,
    this.data
)

fun AnswerInlineQuery.toData(): TdApi.AnswerInlineQuery = TdApi.AnswerInlineQuery(
    this.inlineQueryId,
    this.isPersonal,
    this.button.toData(),
    this.results.map { it.toData() }.toTypedArray(),
    this.cacheTime,
    this.nextOffset
)

fun AnswerPreCheckoutQuery.toData(): TdApi.AnswerPreCheckoutQuery = TdApi.AnswerPreCheckoutQuery(
    this.preCheckoutQueryId,
    this.errorMessage
)

fun AnswerShippingQuery.toData(): TdApi.AnswerShippingQuery = TdApi.AnswerShippingQuery(
    this.shippingQueryId,
    this.shippingOptions.map { it.toData() }.toTypedArray(),
    this.errorMessage
)

fun AnswerWebAppQuery.toData(): TdApi.AnswerWebAppQuery = TdApi.AnswerWebAppQuery(
    this.webAppQueryId,
    this.result.toData()
)

