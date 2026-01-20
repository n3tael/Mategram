package com.xxcactussell.data.utils.mappers.json

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.JsonObjectMember.toDomain(): JsonObjectMember = JsonObjectMember(
    key = this.key,
    value = this.value.toDomain()
)

fun TdApi.JsonValue.toDomain(): JsonValue = when(this) {
    is TdApi.JsonValueNull -> this.toDomain()
    is TdApi.JsonValueBoolean -> this.toDomain()
    is TdApi.JsonValueNumber -> this.toDomain()
    is TdApi.JsonValueString -> this.toDomain()
    is TdApi.JsonValueArray -> this.toDomain()
    is TdApi.JsonValueObject -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.JsonValueArray.toDomain(): JsonValueArray = JsonValueArray(
    values = this.values.map { it.toDomain() }
)

fun TdApi.JsonValueBoolean.toDomain(): JsonValueBoolean = JsonValueBoolean(
    value = this.value
)

fun TdApi.JsonValueNull.toDomain(): JsonValueNull = JsonValueNull

fun TdApi.JsonValueNumber.toDomain(): JsonValueNumber = JsonValueNumber(
    value = this.value
)

fun TdApi.JsonValueObject.toDomain(): JsonValueObject = JsonValueObject(
    members = this.members.map { it.toDomain() }
)

fun TdApi.JsonValueString.toDomain(): JsonValueString = JsonValueString(
    value = this.value
)

