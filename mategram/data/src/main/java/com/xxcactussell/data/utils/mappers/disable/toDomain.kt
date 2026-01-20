package com.xxcactussell.data.utils.mappers.disable

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.DisableAllSupergroupUsernames.toDomain(): DisableAllSupergroupUsernames = DisableAllSupergroupUsernames(
    supergroupId = this.supergroupId
)

