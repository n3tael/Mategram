package com.xxcactussell.domain

data class MessagePassportDataSent(
    val types: List<PassportElementType>
) : MessageContent
