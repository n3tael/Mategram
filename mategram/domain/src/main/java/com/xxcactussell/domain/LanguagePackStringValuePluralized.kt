package com.xxcactussell.domain

data class LanguagePackStringValuePluralized(
    val zeroValue: String,
    val oneValue: String,
    val twoValue: String,
    val fewValue: String,
    val manyValue: String,
    val otherValue: String
) : LanguagePackStringValue
