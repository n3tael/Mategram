package com.xxcactussell.data.utils.mappers.localization

import com.xxcactussell.data.utils.mappers.language.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun LocalizationTargetInfo.toData(): TdApi.LocalizationTargetInfo = TdApi.LocalizationTargetInfo(
    this.languagePacks.map { it.toData() }.toTypedArray()
)

