package com.xxcactussell.domain

data class MessageDocument(
    val document: Document,
    val caption: FormattedText
) : MessageContent
