package com.xxcactussell.domain

data class FormattedText(
    val text: String,
    val entities: List<TextEntity> = emptyList()
) : Object
