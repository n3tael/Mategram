package com.xxcactussell.data.utils.mappers.accent

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

fun AccentColor.toData(): TdApi.AccentColor = TdApi.AccentColor(
    this.id,
    this.builtInAccentColorId,
    this.lightThemeColors,
    this.darkThemeColors,
    this.minChannelChatBoostLevel
)

