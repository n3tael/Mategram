package com.xxcactussell.data.utils.mappers.option

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.OptionValue.toDomain(): OptionValue = when(this) {
    is TdApi.OptionValueBoolean -> this.toDomain()
    is TdApi.OptionValueEmpty -> this.toDomain()
    is TdApi.OptionValueInteger -> this.toDomain()
    is TdApi.OptionValueString -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.OptionValueBoolean.toDomain(): OptionValueBoolean = OptionValueBoolean(
    value = this.value
)

fun TdApi.OptionValueEmpty.toDomain(): OptionValueEmpty = OptionValueEmpty

fun TdApi.OptionValueInteger.toDomain(): OptionValueInteger = OptionValueInteger(
    value = this.value
)

fun TdApi.OptionValueString.toDomain(): OptionValueString = OptionValueString(
    value = this.value
)

