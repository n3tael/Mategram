package com.xxcactussell.data.utils.mappers.parse

import com.xxcactussell.data.utils.mappers.formatted.toDomain
import com.xxcactussell.data.utils.mappers.text.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.ParseMarkdown.toDomain(): ParseMarkdown = ParseMarkdown(
    text = this.text.toDomain()
)

fun TdApi.ParseTextEntities.toDomain(): ParseTextEntities = ParseTextEntities(
    text = this.text,
    parseMode = this.parseMode.toDomain()
)

