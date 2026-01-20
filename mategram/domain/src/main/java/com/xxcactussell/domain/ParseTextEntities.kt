package com.xxcactussell.domain

data class ParseTextEntities(
    val text: String,
    val parseMode: TextParseMode
) : Function
