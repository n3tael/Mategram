package com.xxcactussell.data.utils.mappers.accent

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

fun TdApi.AccentColor.toDomain(): AccentColor = AccentColor(
    id = this.id,
    builtInAccentColorId = this.builtInAccentColorId,
    lightThemeColors = this.lightThemeColors,
    darkThemeColors = this.darkThemeColors,
    minChannelChatBoostLevel = this.minChannelChatBoostLevel
)

