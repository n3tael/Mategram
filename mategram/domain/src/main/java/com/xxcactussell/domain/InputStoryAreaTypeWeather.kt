package com.xxcactussell.domain

data class InputStoryAreaTypeWeather(
    val temperature: Double,
    val emoji: String,
    val backgroundColor: Int
) : InputStoryAreaType
