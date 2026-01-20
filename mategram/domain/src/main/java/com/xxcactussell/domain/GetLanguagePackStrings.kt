package com.xxcactussell.domain

data class GetLanguagePackStrings(
    val languagePackId: String,
    val keys: List<String>
) : Function
