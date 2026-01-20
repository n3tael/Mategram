package com.xxcactussell.data.utils.mappers.disable

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun DisableAllSupergroupUsernames.toData(): TdApi.DisableAllSupergroupUsernames = TdApi.DisableAllSupergroupUsernames(
    this.supergroupId
)

