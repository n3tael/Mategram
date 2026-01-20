package com.xxcactussell.domain

data class SearchEmojis(
    val text: String,
    val inputLanguageCodes: List<String>
) : Function
