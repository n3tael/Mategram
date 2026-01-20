package com.xxcactussell.data.utils.mappers.localization

import com.xxcactussell.data.utils.mappers.language.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.LocalizationTargetInfo.toDomain(): LocalizationTargetInfo = LocalizationTargetInfo(
    languagePacks = this.languagePacks.map { it.toDomain() }
)

