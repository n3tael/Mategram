package com.xxcactussell.data.utils.mappers.background

import com.xxcactussell.data.utils.mappers.file.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.Background.toDomain(): Background = Background(
    id = this.id,
    isDefault = this.isDefault,
    isDark = this.isDark,
    name = this.name,
    document = this.document?.toDomain(),
    type = this.type.toDomain()
)

fun TdApi.BackgroundFill.toDomain(): BackgroundFill = when(this) {
    is TdApi.BackgroundFillSolid -> this.toDomain()
    is TdApi.BackgroundFillGradient -> this.toDomain()
    is TdApi.BackgroundFillFreeformGradient -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.BackgroundFillFreeformGradient.toDomain(): BackgroundFillFreeformGradient = BackgroundFillFreeformGradient(
    colors = this.colors
)

fun TdApi.BackgroundFillGradient.toDomain(): BackgroundFillGradient = BackgroundFillGradient(
    topColor = this.topColor,
    bottomColor = this.bottomColor,
    rotationAngle = this.rotationAngle
)

fun TdApi.BackgroundFillSolid.toDomain(): BackgroundFillSolid = BackgroundFillSolid(
    color = this.color
)

fun TdApi.BackgroundType.toDomain(): BackgroundType = when(this) {
    is TdApi.BackgroundTypeWallpaper -> this.toDomain()
    is TdApi.BackgroundTypePattern -> this.toDomain()
    is TdApi.BackgroundTypeFill -> this.toDomain()
    is TdApi.BackgroundTypeChatTheme -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.BackgroundTypeChatTheme.toDomain(): BackgroundTypeChatTheme = BackgroundTypeChatTheme(
    themeName = this.themeName
)

fun TdApi.BackgroundTypeFill.toDomain(): BackgroundTypeFill = BackgroundTypeFill(
    fill = this.fill.toDomain()
)

fun TdApi.BackgroundTypePattern.toDomain(): BackgroundTypePattern = BackgroundTypePattern(
    fill = this.fill.toDomain(),
    intensity = this.intensity,
    isInverted = this.isInverted,
    isMoving = this.isMoving
)

fun TdApi.BackgroundTypeWallpaper.toDomain(): BackgroundTypeWallpaper = BackgroundTypeWallpaper(
    isBlurred = this.isBlurred,
    isMoving = this.isMoving
)

