package com.xxcactussell.domain

data class GetKeywordEmojis(
    val text: String,
    val inputLanguageCodes: List<String>
) : Function
