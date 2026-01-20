package com.xxcactussell.data.utils.mappers.synchronize

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.SynchronizeLanguagePack.toDomain(): SynchronizeLanguagePack = SynchronizeLanguagePack(
    languagePackId = this.languagePackId
)

