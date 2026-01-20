package com.xxcactussell.data.utils.mappers.background

import com.xxcactussell.data.utils.mappers.file.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun Background.toData(): TdApi.Background = TdApi.Background(
    this.id,
    this.isDefault,
    this.isDark,
    this.name,
    this.document?.toData(),
    this.type.toData()
)

fun BackgroundFill.toData(): TdApi.BackgroundFill = when(this) {
    is BackgroundFillSolid -> this.toData()
    is BackgroundFillGradient -> this.toData()
    is BackgroundFillFreeformGradient -> this.toData()
}

fun BackgroundFillFreeformGradient.toData(): TdApi.BackgroundFillFreeformGradient = TdApi.BackgroundFillFreeformGradient(
    this.colors
)

fun BackgroundFillGradient.toData(): TdApi.BackgroundFillGradient = TdApi.BackgroundFillGradient(
    this.topColor,
    this.bottomColor,
    this.rotationAngle
)

fun BackgroundFillSolid.toData(): TdApi.BackgroundFillSolid = TdApi.BackgroundFillSolid(
    this.color
)

fun BackgroundType.toData(): TdApi.BackgroundType = when(this) {
    is BackgroundTypeWallpaper -> this.toData()
    is BackgroundTypePattern -> this.toData()
    is BackgroundTypeFill -> this.toData()
    is BackgroundTypeChatTheme -> this.toData()
}

fun BackgroundTypeChatTheme.toData(): TdApi.BackgroundTypeChatTheme = TdApi.BackgroundTypeChatTheme(
    this.themeName
)

fun BackgroundTypeFill.toData(): TdApi.BackgroundTypeFill = TdApi.BackgroundTypeFill(
    this.fill.toData()
)

fun BackgroundTypePattern.toData(): TdApi.BackgroundTypePattern = TdApi.BackgroundTypePattern(
    this.fill.toData(),
    this.intensity,
    this.isInverted,
    this.isMoving
)

fun BackgroundTypeWallpaper.toData(): TdApi.BackgroundTypeWallpaper = TdApi.BackgroundTypeWallpaper(
    this.isBlurred,
    this.isMoving
)

