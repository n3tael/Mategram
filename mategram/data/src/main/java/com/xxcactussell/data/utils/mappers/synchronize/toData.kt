package com.xxcactussell.data.utils.mappers.synchronize

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun SynchronizeLanguagePack.toData(): TdApi.SynchronizeLanguagePack = TdApi.SynchronizeLanguagePack(
    this.languagePackId
)

