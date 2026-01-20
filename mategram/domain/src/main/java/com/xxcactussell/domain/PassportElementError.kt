package com.xxcactussell.domain

data class PassportElementError(
    val type: PassportElementType,
    val message: String,
    val source: PassportElementErrorSource
) : Object
