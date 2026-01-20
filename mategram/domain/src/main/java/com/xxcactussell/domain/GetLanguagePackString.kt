package com.xxcactussell.domain

data class GetLanguagePackString(
    val languagePackDatabasePath: String,
    val localizationTarget: String,
    val languagePackId: String,
    val key: String
) : Function
