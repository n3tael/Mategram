package com.xxcactussell.domain

data class SearchQuote(
    val text: FormattedText,
    val quote: FormattedText,
    val quotePosition: Int
) : Function
