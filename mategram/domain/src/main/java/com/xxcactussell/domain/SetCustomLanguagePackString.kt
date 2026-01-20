package com.xxcactussell.domain

data class SetCustomLanguagePackString(
    val languagePackId: String,
    val newString: LanguagePackString
) : Function
