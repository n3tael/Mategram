package com.xxcactussell.data.utils.mappers.current

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun CurrentWeather.toData(): TdApi.CurrentWeather = TdApi.CurrentWeather(
    this.temperature,
    this.emoji
)

