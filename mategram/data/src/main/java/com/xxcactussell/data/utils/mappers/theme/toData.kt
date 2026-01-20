package com.xxcactussell.data.utils.mappers.theme

import com.xxcactussell.data.utils.mappers.background.toData
import com.xxcactussell.data.utils.mappers.built.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun ThemeParameters.toData(): TdApi.ThemeParameters = TdApi.ThemeParameters(
    this.backgroundColor,
    this.secondaryBackgroundColor,
    this.headerBackgroundColor,
    this.bottomBarBackgroundColor,
    this.sectionBackgroundColor,
    this.sectionSeparatorColor,
    this.textColor,
    this.accentTextColor,
    this.sectionHeaderTextColor,
    this.subtitleTextColor,
    this.destructiveTextColor,
    this.hintColor,
    this.linkColor,
    this.buttonColor,
    this.buttonTextColor
)

fun ThemeSettings.toData(): TdApi.ThemeSettings = TdApi.ThemeSettings(
    this.baseTheme.toData(),
    this.accentColor,
    this.background?.toData(),
    this.outgoingMessageFill?.toData(),
    this.animateOutgoingMessageFill,
    this.outgoingMessageAccentColor
)

