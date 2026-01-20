package com.xxcactussell.data.utils.mappers.language

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun LanguagePackInfo.toData(): TdApi.LanguagePackInfo = TdApi.LanguagePackInfo(
    this.id,
    this.baseLanguagePackId,
    this.name,
    this.nativeName,
    this.pluralCode,
    this.isOfficial,
    this.isRtl,
    this.isBeta,
    this.isInstalled,
    this.totalStringCount,
    this.translatedStringCount,
    this.localStringCount,
    this.translationUrl
)

fun LanguagePackString.toData(): TdApi.LanguagePackString = TdApi.LanguagePackString(
    this.key,
    this.value.toData()
)

fun LanguagePackStringValue.toData(): TdApi.LanguagePackStringValue = when(this) {
    is LanguagePackStringValueOrdinary -> this.toData()
    is LanguagePackStringValuePluralized -> this.toData()
    is LanguagePackStringValueDeleted -> this.toData()
}

fun LanguagePackStringValueDeleted.toData(): TdApi.LanguagePackStringValueDeleted = TdApi.LanguagePackStringValueDeleted(
)

fun LanguagePackStringValueOrdinary.toData(): TdApi.LanguagePackStringValueOrdinary = TdApi.LanguagePackStringValueOrdinary(
    this.value
)

fun LanguagePackStringValuePluralized.toData(): TdApi.LanguagePackStringValuePluralized = TdApi.LanguagePackStringValuePluralized(
    this.zeroValue,
    this.oneValue,
    this.twoValue,
    this.fewValue,
    this.manyValue,
    this.otherValue
)

fun LanguagePackStrings.toData(): TdApi.LanguagePackStrings = TdApi.LanguagePackStrings(
    this.strings.map { it.toData() }.toTypedArray()
)

