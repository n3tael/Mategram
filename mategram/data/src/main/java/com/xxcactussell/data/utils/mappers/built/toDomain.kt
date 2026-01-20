package com.xxcactussell.data.utils.mappers.built

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.BuiltInTheme.toDomain(): BuiltInTheme = when(this) {
    is TdApi.BuiltInThemeClassic -> this.toDomain()
    is TdApi.BuiltInThemeDay -> this.toDomain()
    is TdApi.BuiltInThemeNight -> this.toDomain()
    is TdApi.BuiltInThemeTinted -> this.toDomain()
    is TdApi.BuiltInThemeArctic -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.BuiltInThemeArctic.toDomain(): BuiltInThemeArctic = BuiltInThemeArctic

fun TdApi.BuiltInThemeClassic.toDomain(): BuiltInThemeClassic = BuiltInThemeClassic

fun TdApi.BuiltInThemeDay.toDomain(): BuiltInThemeDay = BuiltInThemeDay

fun TdApi.BuiltInThemeNight.toDomain(): BuiltInThemeNight = BuiltInThemeNight

fun TdApi.BuiltInThemeTinted.toDomain(): BuiltInThemeTinted = BuiltInThemeTinted

