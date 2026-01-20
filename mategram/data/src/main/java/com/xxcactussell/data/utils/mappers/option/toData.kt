package com.xxcactussell.data.utils.mappers.option

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun OptionValue.toData(): TdApi.OptionValue = when(this) {
    is OptionValueBoolean -> this.toData()
    is OptionValueEmpty -> this.toData()
    is OptionValueInteger -> this.toData()
    is OptionValueString -> this.toData()
}

fun OptionValueBoolean.toData(): TdApi.OptionValueBoolean = TdApi.OptionValueBoolean(
    this.value
)

fun OptionValueEmpty.toData(): TdApi.OptionValueEmpty = TdApi.OptionValueEmpty(
)

fun OptionValueInteger.toData(): TdApi.OptionValueInteger = TdApi.OptionValueInteger(
    this.value
)

fun OptionValueString.toData(): TdApi.OptionValueString = TdApi.OptionValueString(
    this.value
)

