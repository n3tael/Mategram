package com.xxcactussell.data.utils.mappers.formatted

import com.xxcactussell.data.utils.mappers.text.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.FormattedText.toDomain(): FormattedText = FormattedText(
    text = this.text,
    entities = this.entities.map { it.toDomain() }
)

