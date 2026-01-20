package com.xxcactussell.domain

data class StoryAreaTypeWeather(
    val temperature: Double,
    val emoji: String,
    val backgroundColor: Int
) : StoryAreaType
