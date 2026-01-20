package com.xxcactussell.domain

data class SetCustomLanguagePack(
    val info: LanguagePackInfo,
    val strings: List<LanguagePackString>
) : Function
