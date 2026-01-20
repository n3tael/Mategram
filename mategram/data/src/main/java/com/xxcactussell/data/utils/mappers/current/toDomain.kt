package com.xxcactussell.data.utils.mappers.current

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.CurrentWeather.toDomain(): CurrentWeather = CurrentWeather(
    temperature = this.temperature,
    emoji = this.emoji
)

