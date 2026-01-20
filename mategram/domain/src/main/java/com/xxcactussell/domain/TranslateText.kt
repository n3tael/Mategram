package com.xxcactussell.domain

data class TranslateText(
    val text: FormattedText,
    val toLanguageCode: String
) : Function
