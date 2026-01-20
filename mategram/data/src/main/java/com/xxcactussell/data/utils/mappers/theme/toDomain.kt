package com.xxcactussell.data.utils.mappers.theme

import com.xxcactussell.data.utils.mappers.background.toDomain
import com.xxcactussell.data.utils.mappers.built.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.ThemeParameters.toDomain(): ThemeParameters = ThemeParameters(
    backgroundColor = this.backgroundColor,
    secondaryBackgroundColor = this.secondaryBackgroundColor,
    headerBackgroundColor = this.headerBackgroundColor,
    bottomBarBackgroundColor = this.bottomBarBackgroundColor,
    sectionBackgroundColor = this.sectionBackgroundColor,
    sectionSeparatorColor = this.sectionSeparatorColor,
    textColor = this.textColor,
    accentTextColor = this.accentTextColor,
    sectionHeaderTextColor = this.sectionHeaderTextColor,
    subtitleTextColor = this.subtitleTextColor,
    destructiveTextColor = this.destructiveTextColor,
    hintColor = this.hintColor,
    linkColor = this.linkColor,
    buttonColor = this.buttonColor,
    buttonTextColor = this.buttonTextColor
)

fun TdApi.ThemeSettings.toDomain(): ThemeSettings = ThemeSettings(
    baseTheme = this.baseTheme.toDomain(),
    accentColor = this.accentColor,
    background = this.background?.toDomain(),
    outgoingMessageFill = this.outgoingMessageFill?.toDomain(),
    animateOutgoingMessageFill = this.animateOutgoingMessageFill,
    outgoingMessageAccentColor = this.outgoingMessageAccentColor
)

