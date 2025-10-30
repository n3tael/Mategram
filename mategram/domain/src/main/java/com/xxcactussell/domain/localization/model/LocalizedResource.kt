package com.xxcactussell.domain.localization.model

sealed interface TranslationValue {
    data class Ordinary(val value: String) : TranslationValue
    data class Pluralized(
        val zero: String? = null,
        val one: String? = null,
        val two: String? = null,
        val few: String? = null,
        val many: String? = null,
        val other: String
    ) : TranslationValue
    object Deleted : TranslationValue
}

data class LocalizedResource(
    val key: String,
    val value: TranslationValue
)