package com.xxcactussell.domain

data class UpdateLanguagePackStrings(
    val localizationTarget: String,
    val languagePackId: String,
    val strings: List<LanguagePackString>
) : Update
