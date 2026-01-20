package com.xxcactussell.data.utils.mappers.formatted

import com.xxcactussell.data.utils.mappers.text.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun FormattedText.toData(): TdApi.FormattedText = TdApi.FormattedText(
    this.text,
    this.entities.map { it.toData() }.toTypedArray()
)

