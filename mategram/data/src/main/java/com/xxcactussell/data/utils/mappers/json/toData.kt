package com.xxcactussell.data.utils.mappers.json

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun JsonObjectMember.toData(): TdApi.JsonObjectMember = TdApi.JsonObjectMember(
    this.key,
    this.value.toData()
)

fun JsonValue.toData(): TdApi.JsonValue = when(this) {
    is JsonValueNull -> this.toData()
    is JsonValueBoolean -> this.toData()
    is JsonValueNumber -> this.toData()
    is JsonValueString -> this.toData()
    is JsonValueArray -> this.toData()
    is JsonValueObject -> this.toData()
}

fun JsonValueArray.toData(): TdApi.JsonValueArray = TdApi.JsonValueArray(
    this.values.map { it.toData() }.toTypedArray()
)

fun JsonValueBoolean.toData(): TdApi.JsonValueBoolean = TdApi.JsonValueBoolean(
    this.value
)

fun JsonValueNull.toData(): TdApi.JsonValueNull = TdApi.JsonValueNull(
)

fun JsonValueNumber.toData(): TdApi.JsonValueNumber = TdApi.JsonValueNumber(
    this.value
)

fun JsonValueObject.toData(): TdApi.JsonValueObject = TdApi.JsonValueObject(
    this.members.map { it.toData() }.toTypedArray()
)

fun JsonValueString.toData(): TdApi.JsonValueString = TdApi.JsonValueString(
    this.value
)

