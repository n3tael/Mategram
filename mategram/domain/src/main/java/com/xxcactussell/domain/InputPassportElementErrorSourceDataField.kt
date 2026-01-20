package com.xxcactussell.domain

data class InputPassportElementErrorSourceDataField(
    val fieldName: String,
    val dataHash: ByteArray
) : InputPassportElementErrorSource
