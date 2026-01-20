package com.xxcactussell.data.utils.mappers.built

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun BuiltInTheme.toData(): TdApi.BuiltInTheme = when(this) {
    is BuiltInThemeClassic -> this.toData()
    is BuiltInThemeDay -> this.toData()
    is BuiltInThemeNight -> this.toData()
    is BuiltInThemeTinted -> this.toData()
    is BuiltInThemeArctic -> this.toData()
}

fun BuiltInThemeArctic.toData(): TdApi.BuiltInThemeArctic = TdApi.BuiltInThemeArctic(
)

fun BuiltInThemeClassic.toData(): TdApi.BuiltInThemeClassic = TdApi.BuiltInThemeClassic(
)

fun BuiltInThemeDay.toData(): TdApi.BuiltInThemeDay = TdApi.BuiltInThemeDay(
)

fun BuiltInThemeNight.toData(): TdApi.BuiltInThemeNight = TdApi.BuiltInThemeNight(
)

fun BuiltInThemeTinted.toData(): TdApi.BuiltInThemeTinted = TdApi.BuiltInThemeTinted(
)

