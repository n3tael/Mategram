package com.xxcactussell.data.utils.mappers.hide

import com.xxcactussell.data.utils.mappers.suggested.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun HideContactCloseBirthdays.toData(): TdApi.HideContactCloseBirthdays = TdApi.HideContactCloseBirthdays(
)

fun HideSuggestedAction.toData(): TdApi.HideSuggestedAction = TdApi.HideSuggestedAction(
    this.action.toData()
)

