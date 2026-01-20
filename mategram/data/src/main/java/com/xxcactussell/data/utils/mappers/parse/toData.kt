package com.xxcactussell.data.utils.mappers.parse

import com.xxcactussell.data.utils.mappers.formatted.toData
import com.xxcactussell.data.utils.mappers.text.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun ParseMarkdown.toData(): TdApi.ParseMarkdown = TdApi.ParseMarkdown(
    this.text.toData()
)

fun ParseTextEntities.toData(): TdApi.ParseTextEntities = TdApi.ParseTextEntities(
    this.text,
    this.parseMode.toData()
)

