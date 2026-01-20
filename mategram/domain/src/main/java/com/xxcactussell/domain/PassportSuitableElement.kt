package com.xxcactussell.domain

data class PassportSuitableElement(
    val type: PassportElementType,
    val isSelfieRequired: Boolean,
    val isTranslationRequired: Boolean,
    val isNativeNameRequired: Boolean
) : Object
