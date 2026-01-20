package com.xxcactussell.repositories.localization.model

data class LanguagePack(
    val id: String,
    val baseLanguagePackId: String,
    val name: String,
    val nativeName: String,
    val pluralCode: String,
    val isOfficial: Boolean,
    val isRtl: Boolean,
    val isBeta: Boolean,
    val isInstalled: Boolean,
    val totalStringCount: Int,
    val translatedStringCount: Int,
    val localStringCount: Int,
    val translationUrl: String
)