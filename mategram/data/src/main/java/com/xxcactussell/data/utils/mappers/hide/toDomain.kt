package com.xxcactussell.data.utils.mappers.hide

import com.xxcactussell.data.utils.mappers.suggested.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.HideContactCloseBirthdays.toDomain(): HideContactCloseBirthdays = HideContactCloseBirthdays

fun TdApi.HideSuggestedAction.toDomain(): HideSuggestedAction = HideSuggestedAction(
    action = this.action.toDomain()
)

