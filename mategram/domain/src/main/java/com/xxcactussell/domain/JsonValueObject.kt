package com.xxcactussell.domain

data class JsonValueObject(
    val members: List<JsonObjectMember>
) : JsonValue
