package com.xxcactussell.domain

data class InputPassportElementError(
    val type: PassportElementType,
    val message: String,
    val source: InputPassportElementErrorSource
) : Object
