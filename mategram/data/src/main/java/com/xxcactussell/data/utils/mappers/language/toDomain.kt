package com.xxcactussell.data.utils.mappers.language

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.LanguagePackInfo.toDomain(): LanguagePackInfo = LanguagePackInfo(
    id = this.id,
    baseLanguagePackId = this.baseLanguagePackId,
    name = this.name,
    nativeName = this.nativeName,
    pluralCode = this.pluralCode,
    isOfficial = this.isOfficial,
    isRtl = this.isRtl,
    isBeta = this.isBeta,
    isInstalled = this.isInstalled,
    totalStringCount = this.totalStringCount,
    translatedStringCount = this.translatedStringCount,
    localStringCount = this.localStringCount,
    translationUrl = this.translationUrl
)

fun TdApi.LanguagePackString.toDomain(): LanguagePackString = LanguagePackString(
    key = this.key,
    value = this.value.toDomain()
)

fun TdApi.LanguagePackStringValue.toDomain(): LanguagePackStringValue = when(this) {
    is TdApi.LanguagePackStringValueOrdinary -> this.toDomain()
    is TdApi.LanguagePackStringValuePluralized -> this.toDomain()
    is TdApi.LanguagePackStringValueDeleted -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.LanguagePackStringValueDeleted.toDomain(): LanguagePackStringValueDeleted = LanguagePackStringValueDeleted

fun TdApi.LanguagePackStringValueOrdinary.toDomain(): LanguagePackStringValueOrdinary = LanguagePackStringValueOrdinary(
    value = this.value
)

fun TdApi.LanguagePackStringValuePluralized.toDomain(): LanguagePackStringValuePluralized = LanguagePackStringValuePluralized(
    zeroValue = this.zeroValue,
    oneValue = this.oneValue,
    twoValue = this.twoValue,
    fewValue = this.fewValue,
    manyValue = this.manyValue,
    otherValue = this.otherValue
)

fun TdApi.LanguagePackStrings.toDomain(): LanguagePackStrings = LanguagePackStrings(
    strings = this.strings.map { it.toDomain() }
)

